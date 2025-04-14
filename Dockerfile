FROM nginx:stable

COPY front-projetoum /usr/share/nginx/html
COPY nginx.conf /etc/nginx/nginx.conf
COPY src/env.js /usr/share/nginx/html/assets/env.js

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]
