Prescription AI System

About This Project
This project is designed to make prescription understanding a bit smarter and more useful.
The idea is simple — when a user uploads a prescription image, the system analyzes it and suggests relevant exercise videos.
But instead of just calling the AI every time (which is inefficient and costly), I built the system in a smarter way using hash-based caching and database-first logic.
This makes the system faster, more efficient, and scalable.

What Problem It Solves
In a normal system:
•	Every upload → AI is called 
•	This increases cost and response time
In this project:
•	Same image → same result reused 
•	AI is only called when necessary 

How the System Works (Step-by-Step)
Here’s the complete flow in simple terms:
1.	The user uploads a prescription image
2.	The backend reads the image as bytes
3.	A unique hash (SHA-256) is generated for that image
4.	The system checks the database:
o	If the hash already exists → return stored result
o	If not → call AI → save result → return response

Example Flow
First Upload:
•	Image is new
•	Not found in DB
•	AI is called
•	Result is saved
Output:
New Result: Recommended Exercise Video
Second Upload (Same Image):
•	Same hash generated
•	Found in DB
•	AI is NOT called
Output:
From DB: Recommended Exercise Video

Technologies Used:
Backend
•	Spring Boot (Java)
•	REST APIs
Database
•	H2 (used during development)
•	PostgreSQL (can be used in production)
API Communication
•	WebClient (Reactive programming)

Key Features:
1. Image Upload API
•	Users can upload prescription images using multipart API
2. Hash-Based Caching
•	Each image generates a unique SHA-256 hash
•	Prevents duplicate processing
3. Database-First Logic
•	System checks database before calling AI
•	Reduces API usage and improves performance
4. AI Integration (via WebClient)
•	Uses WebClient for calling external AI services
•	Non-blocking and scalable
5. Error Handling
•	If image is empty:
→ "Please retake the image clearly!"
•	If processing fails:
→ "Unable to process image. Please try again."
6. Secure API Key Handling
•	API key is NOT hardcoded
•	Stored using environment variables
•	Protected using .gitignore

Security Implementation:
To ensure security:
•	API keys are stored in:
•	application.properties (or environment variables)
•	Sensitive files are ignored using:
•	.gitignore
This prevents accidental exposure of secrets on GitHub.

How to Run the Project:
Open terminal inside project folder:
   cd E:\demo\demo
   mvnw.cmd spring-boot:run
Server will start on:
    http://localhost:9090

API Endpoint
Upload Prescription Image:
  POST /upload
Example Using cURL:
  curl -X POST http://localhost:9090/upload -F "file=@E:\myimage.jpg"

Sample Output
First Request:
New Result: Recommended Exercise Video
Second Request:
From DB: Recommended Exercise Video

Evaluation Criteria Covered:
This project satisfies all required evaluation points:
✔ Resilient Error Handling
✔ Database-first Logic
✔ Secure Secret Management
✔ Reactive Communication using WebClient

	What I Learned
While building this project, I learned:
•	How to design efficient backend systems
•	How to reduce unnecessary API usage
•	Importance of caching and hashing
•	Secure handling of API keys
•	Basics of reactive programming using WebClient

