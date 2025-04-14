FROM nginx:stable


COPY --from=build /app/dist/front-projetoum /usr/share/nginx/html
COPY src/env.js /usr/share/nginx/html/assets/env.js
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]
