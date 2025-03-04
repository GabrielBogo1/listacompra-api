import { User } from "../models/user/user";

export const environment = {
    DEV_EMAIL: "dev@gmail.com",
    DEV_MASTERKEY: "dev123",
    SERVIDOR: "http://localhost:8080",
    DEV_USER: new User(9999, "Dev", "dev@gmail.com", "dev@gmail.com", "DEV123"),
    PAYPAL_CLIENT_ID: "AUAwy5PH0xQhWhSXOGT1nqTPjI5pbTOtHM52d2OaUxBV9bdBvNO2qBim3a00gRZI_IfPiZcIzqWfCpGt",
    PAYPAL_SECRET: "EJTz4FBiOUk8laEWi3yqbD2l8GXGX7PqxW5U-lzgLciVhRY2OX6JEdTQneTGU9CyARyUSXGkGAXTStWI"
};