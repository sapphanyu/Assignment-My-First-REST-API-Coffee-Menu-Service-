# Assignment: My First REST API — "Coffee Menu Service"
# 673380066-4 สัพพัญญู คำตุ้ม

> **รายวิชา:** CP353002 — Principles of Software Design and Development
> **เทคโนโลยี:** Java 17+ (ทดสอบบน Java 21), Spring Boot 3.x, Maven Wrapper
> **รูปแบบการเก็บข้อมูล:** In-memory List (`ArrayList`)

---

## 📌 1. ภาพรวมโปรเจกต์ (Mini Project Overview)

โปรเจกต์นี้เป็น **RESTful API** สำหรับจัดการระบบเมนูกาแฟ (**Coffee Menu Service**) ที่พัฒนาด้วย **Spring Boot 3.x** โดยเน้นการออกแบบตามหลักสถาปัตยกรรม 3 ชั้น (**Layered Architecture**) เพื่อแยกหน้าที่การทำงาน (Separation of Concerns) ออกจากกันอย่างชัดเจนตามหลัก **Single Responsibility Principle (SRP)**

โปรเจกต์นี้รองรับการทำงานพื้นฐานตามมาตรฐาน CRUD (Create, Read, Update, Delete) ผ่าน HTTP Methods: `GET`, `POST`, `PUT`, และ `DELETE`

---

## 🏗️ 2. โครงสร้างไฟล์และแพ็กเกจ (Project Structure)

โปรเจกต์นี้จัดวางโครงสร้างโค้ดแบบ 3 ชั้น (Layered Design) ดังนี้:

```text
coffee-service/
├── .mvn/
│   └── wrapper/                 # Maven Wrapper configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/coffee_service/
│   │   │       ├── controller/
│   │   │       │   └── CoffeeController.java  # [Controller Layer] รับ-ส่ง HTTP Requests & JSON Responses
│   │   │       ├── model/
│   │   │       │   └── Coffee.java            # [Model Layer] คลาสโครงสร้างข้อมูลเมนูกาแฟ (id, name, price)
│   │   │       ├── service/
│   │   │       │   └── CoffeeService.java     # [Service Layer] ประมวลผล Business Logic และเก็บข้อมูลใน Memory
│   │   │       └── CoffeeServiceApplication.java # Main Application Runner
│   │   └── resources/
│   │       └── application.properties        # Configuration file
│   └── test/
├── mvnw                         # Maven Wrapper script (Linux/macOS)
├── mvnw.cmd                     # Maven Wrapper script (Windows)
└── pom.xml                      # Project Dependencies (Spring Web)
```

### หน้าที่ของแต่ละ Layer

* **Model Layer (`model/Coffee.java`):** กำหนดแอตทริบิวต์ของกาแฟ (`id`, `name`, `price`) พร้อม Getters/Setters และ Constructor
* **Service Layer (`service/CoffeeService.java`):** จัดการข้อมูล `List<Coffee>` ใน Memory รวมถึง Business Logic ในการเพิ่ม, ค้นหา, แก้ไข, ลบข้อมูล และเพิ่ม ID อัตโนมัติ (`AtomicLong`)
* **Controller Layer (`controller/CoffeeController.java`):** รับ Request จาก Client ผ่าน Endpoints ต่างๆ คืนค่า HTTP Status Code (เช่น `200 OK`, `201 Created`, `404 Not Found`) และ Response ในรูปแบบ JSON

---

## 🚀 3. วิธีการติดตั้งและรันโปรเจกต์ (How to Run)

### 3.1 สิ่งที่ต้องเตรียม (Prerequisites)

* **Java JDK:** เวอร์ชัน 17 หรือสูงกว่า (สามารถตรวจสอบด้วยคำสั่ง `java -version`)
* **VS Code** (หรือ IDE อื่นๆ)

### 3.2 ขั้นตอนการรันโปรเจกต์

1. เปิด Terminal ใน VS Code หรือ PowerShell
2. ย้ายตำแหน่ง Terminal เข้าไปที่โฟลเดอร์ `coffee-service`:

   ```powershell
   cd coffee-service
   ```

3. รันโปรเจกต์ด้วยคำสั่ง Maven Wrapper:

   **สำหรับ Windows (PowerShell / Command Prompt):**
   ```powershell
   .\mvnw spring-boot:run
   ```

   **สำหรับ macOS / Linux:**
   ```bash
   ./mvnw spring-boot:run
   ```

4. เมื่อรันสำเร็จ จะปรากฏข้อความ `Started CoffeeServiceApplication in ... seconds` และระบบจะเปิดทำงานที่:
   👉 `http://localhost:8080`

---

## 📡 4. รายละเอียด API Endpoints & ตัวอย่างการใช้งาน

| No. | HTTP Method | Endpoint Path | รายละเอียดการทำงาน | HTTP Status ที่คืนค่า |
|---|---|---|---|---|
| 1 | **GET** | `/coffees` | ดึงรายการเมนูกาแฟทั้งหมดในระบบ | `200 OK` |
| 2 | **GET** | `/coffees/{id}` | ดึงข้อมูลเมนูกาแฟเฉพาะรายการตาม ID | `200 OK` / `404 Not Found` |
| 3 | **POST** | `/coffees` | เพิ่มเมนูกาแฟใหม่เข้าสู่ระบบ | `201 Created` |
| 4 | **PUT** | `/coffees/{id}` | แก้ไขข้อมูลเมนูกาแฟเดิมตาม ID | `200 OK` / `404 Not Found` |
| 5 | **DELETE** | `/coffees/{id}` | ลบเมนูกาแฟออกจากระบบตาม ID | `200 OK` / `404 Not Found` |

---

## 💻 5. ตัวอย่างคำสั่ง cURL สำหรับทดสอบ API

คุณสามารถคัดลอกคำสั่ง cURL ด้านล่างนี้ไปยิงทดสอบใน Terminal / Command Prompt ได้ทันที:

### 5.1 ดูเมนูกาแฟทั้งหมด (`GET /coffees`)

```bash
curl -X GET http://localhost:8080/coffees
```

**Response (200 OK):**
```json
[
  { "id": 1, "name": "Espresso", "price": 45.0 },
  { "id": 2, "name": "Latte", "price": 55.0 }
]
```

---

### 5.2 ดูเมนูกาแฟตาม ID (`GET /coffees/{id}`)

```bash
curl -X GET http://localhost:8080/coffees/1
```

**Response (200 OK):**
```json
{ "id": 1, "name": "Espresso", "price": 45.0 }
```

---

### 5.3 เพิ่มเมนูกาแฟใหม่ (`POST /coffees`)

```bash
curl -X POST http://localhost:8080/coffees \
     -H "Content-Type: application/json" \
     -d "{\"name\": \"Cappuccino\", \"price\": 60.0}"
```

**Response (201 Created):**
```json
{ "id": 3, "name": "Cappuccino", "price": 60.0 }
```

---

### 5.4 แก้ไขเมนูกาแฟตาม ID (`PUT /coffees/{id}`)

```bash
curl -X PUT http://localhost:8080/coffees/2 \
     -H "Content-Type: application/json" \
     -d "{\"name\": \"Latte\", \"price\": 50.0}"
```

**Response (200 OK):**
```json
{ "id": 2, "name": "Latte", "price": 50.0 }
```

---

### 5.5 ลบเมนูกาแฟตาม ID (`DELETE /coffees/{id}`)

```bash
curl -X DELETE http://localhost:8080/coffees/3
```

**Response (200 OK):** *(Empty Response Body)*

---

### 5.6 [Bonus Case] ค้นหา ID ที่ไม่มีในระบบ (`GET /coffees/999`)

```bash
curl -X GET http://localhost:8080/coffees/999
```

**Response (404 Not Found):** *(Empty Response Body)*

---

## 👥 รายละเอียดการส่งงาน

* **รายวิชา:** CP353002 Principles of Software Design and Development
* **Repository:** https://github.com/sapphanyu/Assignment-My-First-REST-API-Coffee-Menu-Service-
