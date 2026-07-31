# Assignment: My First REST API - Coffee Menu Service
# 673380066-4 นายสัพพัญญู คำตุ้ม

สรุปโปรเจกต์
---------------
โปรเจกต์นี้เป็น mini project ที่สร้าง RESTful API สำหรับจัดการเมนูเครื่องดื่มกาแฟ (Coffee Menu Service) โดยมีฟังก์ชันพื้นฐานสำหรับสร้าง อ่าน อัปเดต และลบ (CRUD) เมนูเครื่องดื่ม

โครงสร้างไฟล์ (ตัวอย่าง)
-------------------------
- /src
	- app.py (หรือ main application file)
	- routes.py (ประกอบด้วย endpoints)
	- models.py (data models / schema ถ้าใช้)
	- service.py (business logic)
- requirements.txt
- README.md

วิธีการติดตั้งและรันโปรเจกต์ (How to Run)
-------------------------------------------
1. โคลนโปรเจกต์:
	 git clone <repository-url>
2. เข้าสู่โฟลเดอร์โปรเจกต์:
	 cd Assignment-My-First-REST-API-Coffee-Menu-Service-
3. สร้าง virtual environment (แนะนำ):
	 python -m venv venv
	 source venv/bin/activate   # Linux/macOS
	 venv\Scripts\activate    # Windows
4. ติดตั้ง dependencies:
	 pip install -r requirements.txt
5. รันแอป:
	 python src/app.py

โดยปกติแอปจะรันที่ http://localhost:5000 (หรือพอร์ตที่กำหนดในไฟล์ app)

รายละเอียด API Endpoints & ตัวอย่างการใช้งาน
---------------------------------------------
ตัวอย่าง endpoints (สมมติ API ใช้ base URL : http://localhost:5000/api)

1) GET /api/coffees
	 - คำอธิบาย: ดึงรายการเมนูเครื่องดื่มทั้งหมด
	 - ตัวอย่าง Response:
		 200 OK
		 [
			 {"id": 1, "name": "Espresso", "price": 40},
			 {"id": 2, "name": "Latte", "price": 60}
		 ]

2) GET /api/coffees/{id}
	 - คำอธิบาย: ดึงข้อมูลเครื่องดื่มตาม id
	 - ตัวอย่าง Response:
		 200 OK
		 {"id": 1, "name": "Espresso", "price": 40}

3) POST /api/coffees
	 - คำอธิบาย: สร้างเมนูเครื่องดื่มใหม่
	 - ตัวอย่าง Request Body (JSON):
		 {"name": "Cappuccino", "price": 55}
	 - ตัวอย่าง Response:
		 201 Created
		 {"id": 3, "name": "Cappuccino", "price": 55}

4) PUT /api/coffees/{id}
	 - คำอธิบาย: อัปเดตข้อมูลเมนูเครื่องดื่ม
	 - ตัวอย่าง Request Body (JSON):
		 {"name": "Caffe Latte", "price": 65}
	 - ตัวอย่าง Response:
		 200 OK
		 {"id": 2, "name": "Caffe Latte", "price": 65}

5) DELETE /api/coffees/{id}
	 - คำอธิบาย: ลบเมนูเครื่องดื่ม
	 - ตัวอย่าง Response:
		 204 No Content

ตัวอย่างคำสั่ง cURL สำหรับทดสอบ API
-----------------------------------
# 1) ดึงรายการทั้งหมด
curl -X GET http://localhost:5000/api/coffees

# 2) ดึงรายการตาม id
curl -X GET http://localhost:5000/api/coffees/1

# 3) สร้างเมนูใหม่
curl -X POST http://localhost:5000/api/coffees \
	-H "Content-Type: application/json" \
	-d "{\"name\": \"Cappuccino\", \"price\": 55}"

# 4) อัปเดตเมนู
curl -X PUT http://localhost:5000/api/coffees/2 \
	-H "Content-Type: application/json" \
	-d "{\"name\": \"Caffe Latte\", \"price\": 65}"

# 5) ลบเมนู
curl -X DELETE http://localhost:5000/api/coffees/3

หมายเหตุ
---------
- ปรับ URL และพอร์ตให้ตรงกับการตั้งค่าโปรเจกต์จริง
- หากใช้ฐานข้อมูล ให้ตั้งค่า connection string ในไฟล์ config หรือ environment variables

``` 
