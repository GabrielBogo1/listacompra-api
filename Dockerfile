FROM node:22-bullseye-slim AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN apt-get update && apt-get install -y google-chrome-stable

RUN npm run build --prod

FROM nginx:stable

RUN apt-get update && apt-get install -y curl


COPY --from=build /app/dist/front-projetoum /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
