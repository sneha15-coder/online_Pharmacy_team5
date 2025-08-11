Online Pharmacy Backend - Full version
Includes: User, Drug, Cart, Order, SecurityConfig (basic), Lombok used.

How to run:
1. Update src/main/resources/application.properties with your MySQL credentials.
2. Create database db3 (or change URL to an existing db).
3. mvn clean install
4. mvn spring-boot:run

API Examples:
- GET /api/drugs
- POST /api/auth/login?username=admin&password=admin123
- POST /api/cart/add?username=admin&drugId=1&quantity=2
- GET /api/cart/{username}
- POST /api/orders/place?username=admin
- GET /api/orders/user/{username}
- Drug management (admin required): POST/PUT/DELETE /api/drugs
