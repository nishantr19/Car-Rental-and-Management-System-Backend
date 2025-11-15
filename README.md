Full-Stack Car Rental & Management System

A complete car rental platform featuring secure authentication, real-time vehicle availability, dynamic pricing, and an admin-friendly fleet management dashboard. Built with Spring Boot (JWT) on the backend and React + Vite on the frontend.

📌 Features

🔐 User registration & JWT-based login

🚘 Real-time car availability list

💵 Dynamic pricing based on duration & admin-set rates

📅 Booking creation with automatic validation

🛠 Admin fleet management: add cars, update rates, track bookings

⚡ Fully synchronized backend + frontend

🧪 Ready-to-run full-stack testing workflow

🛠 Technologies

Backend: Java 17+, Spring Boot, Spring Security (JWT), Maven, H2 Databse

Frontend: React, Vite, JavaScript, Fetch API

Tools: curl, jq (optional), Browser DevTools, Postman (optional)

Default Ports:

Backend → http://localhost:8080

Frontend → http://localhost:5173

🚀 Getting Started
1️⃣ Start Backend
cd path/to/backend
mvn spring-boot:run


Optional H2 Console:
http://localhost:8080/h2-console


2️⃣ Start Frontend
cd path/to/frontend
npm install
npm run dev


Open the printed Vite URL (default http://localhost:5173).

🧪 Quick Manual End-to-End Test

Open the frontend

Register → redirect to login

Login → token saved to localStorage

Cars page displays /api/cars/available

Book a car → choose start/end datetime

Verify booking in backend logs or H2 Console

🔗 Useful API Testing (curl)
Register
curl -X POST http://localhost:8080/api/auth/register \
 -H "Content-Type: application/json" \
 -d '{"username":"test","password":"123","email":"t@mail.com"}'

Login + Token Extract
RESP=$(curl -s -X POST http://localhost:8080/api/auth/login \
 -H "Content-Type: application/json" \
 -d '{"username":"test","password":"123"}')

TOKEN=$(echo "$RESP" | jq -r .token)
echo "TOKEN=$TOKEN"

List Cars
curl -H "Authorization: Bearer $TOKEN" \
 http://localhost:8080/api/cars/available | jq

Create Booking
curl -X POST http://localhost:8080/api/bookings/create \
 -H "Authorization: Bearer $TOKEN" \
 -H "Content-Type: application/json" \
 -d '{"carId":"1","start":"2025-11-01T09:00:00","end":"2025-11-03T09:00:00"}' | jq

🤖 Automated Smoke Test

Save as smoke-test.sh → chmod +x smoke-test.sh

#!/usr/bin/env bash
set -e

BASE=http://localhost:8080/api
USER=smoketest
PASS=smokepass
EMAIL=smoke@example.com

echo "Registering user..."
curl -s -X POST $BASE/auth/register \
 -H "Content-Type: application/json" \
 -d "{\"username\":\"$USER\",\"password\":\"$PASS\",\"email\":\"$EMAIL\"}" || true

echo "Logging in..."
RESP=$(curl -s -X POST $BASE/auth/login \
 -H "Content-Type: application/json" \
 -d "{\"username\":\"$USER\",\"password\":\"$PASS\"}")

TOKEN=$(echo "$RESP" | jq -r .token)
echo "Token acquired: $TOKEN"

echo "Listing cars..."
curl -s -H "Authorization: Bearer $TOKEN" $BASE/cars/available | jq

echo "Creating booking..."
curl -s -X POST $BASE/bookings/create \
 -H "Authorization: Bearer $TOKEN" \
 -H "Content-Type: application/json" \
 -d '{"carId":"1","start":"2025-11-01T09:00:00","end":"2025-11-02T09:00:00"}' | jq

🛠 Troubleshooting
❗ CORS errors

Add http://localhost:5173 to backend CORS config

Ensure http.cors() is enabled in SecurityConfig

❗ 401 Unauthorized

Missing/expired token

Token not sent in header

Wrong backend jwt.secret

❗ 403 Forbidden

Security rules blocking endpoint

Only /api/auth/** should be permitAll

❗ Empty cars list

Check GET /api/cars/available in Network tab

Ensure seed cars exist

☑️ Verification Checklist

 Backend running on 8080

 Frontend running on 5173

 Register works

 Login stores JWT in localStorage

 Available cars load correctly

 Booking creation works

 Booking visible in H2 or logs
