FROM node:22-bullseye-slim AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build --prod

FROM nginx:stable

RUN apt-get update && apt-get install -y curl


COPY --from=build /app/dist/front-projetoum /usr/share/nginx/html
COPY src/env.js /usr/share/nginx/html/assets/env.js
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80 443

CMD ["nginx", "-g", "daemon off;"]
