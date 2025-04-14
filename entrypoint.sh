#!/bin/sh
set -e # Sai imediatamente se um comando falhar

# Verifica variáveis de ambiente essenciais
: "${api-mensal.shop}"
: "${tiuzaoq@gmail.com}"

# Substitui os placeholders no template Nginx
export DOLLAR='$' # Necessário para variáveis Nginx como $host
envsubst < /etc/nginx/conf.d/default.conf.template > /etc/nginx/conf.d/default.conf

echo ">>> Esperando Nginx iniciar para desafio inicial..."
# Inicia Nginx em background temporariamente para o Certbot --nginx funcionar
nginx -g "daemon on;"

# Pequena pausa para garantir que o Nginx esteja pronto
sleep 5

echo ">>> Tentando obter/renovar certificado para ${DOMAIN}..."

# Roda Certbot para obter o certificado (se não existir) ou renovar
# --nginx: Usa o plugin Nginx para configurar automaticamente
# --agree-tos: Aceita os termos de serviço
# --redirect: Configura Nginx para redirecionar HTTP para HTTPS
# --hsts: Adiciona cabeçalho Strict-Transport-Security
# --stapling: Habilita OCSP Stapling
# --email: Seu e-mail para notificações (importante!)
# -d: O domínio(s) para o certificado
# --non-interactive: Roda sem perguntas interativas
# --keep-until-expiring / --force-renewal (use um ou outro para teste, ou nenhum para produção inicial)
certbot --nginx --agree-tos --redirect --hsts --stapling -d "${DOMAIN}" --email "${EMAIL}" --non-interactive --keep-until-expiring

echo ">>> Certificado obtido/renovado."

# Para Nginx que estava em background
nginx -s stop
sleep 2

# Inicia loop para renovação automática em background
echo ">>> Iniciando loop de renovação automática do Certbot..."
while :; do
  certbot renew --nginx # Tenta renovar, só faz algo se o certificado estiver perto de expirar
  sleep 12h # Verifica a cada 12 horas
done & # Roda o loop em background

# Inicia o Nginx no foreground (comando principal do container)
echo ">>> Iniciando Nginx no foreground..."
exec "$@" # Executa o comando passado para o entrypoint (CMD do Dockerfile)