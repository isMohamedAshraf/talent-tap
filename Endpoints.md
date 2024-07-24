Below are the REST API endpoints for the freelancing platform. These endpoints cover user management, profile management, job postings, proposals, messaging, payment processing, and reviews.

## API Endpoints

### Authentication

#### Register User

- **Endpoint:** `POST auth/register`
- **Description:** Register a new user (freelancer or employer).
- **Request Body:**
  ```json
  {
    "username": "ashraf",
    "firstname": "Mohamed",
    "lastname": "Ashraf",
    "email": "mohamed@ashraf.com",
    "password": "password",
    "isFreelancer": false
  }
  ```
- **Response:**
  ```
  Response code: 202
  ```

#### Login

- **Endpoint:** `POST auth/login`
- **Description:** Authenticate a user and return a JWT token.
- **Request Body:**
  ```json
  {
    "email": "string",
    "password": "string"
  }
  ```
- **Response:**
  ```json
  {
    "token": "eyJhbGciOiJIUzM4NCJ9.eyJmdWxsTmFtZSI6ImFzaHJhZiIsInN1YiI6ImFzaHJhZiIsImlhdCI6MTcyMTgxMTU4MiwiZXhwIjoxNzIxODk3OTgyLCJhdXRob3JpdGllcyI6WyJDTElFTlQiXSwiaXNzIjoiU3lzdGVtIn0.ckewiaU0vVgQiwmU9lmb7fUNhZEMhJqm4bex7p4aXnBF6LzUtoqlJ7cduXJ-Bwfu"
  }
  ```

### User Management

#### Get User Profile

- **Endpoint:** `GET users/:id`
- **Description:** Get the profile information of a user by their ID.
- **Response:**
  ```json
  {
    "id": "string",
    "username": "string",
    "email": "string",
    "role": "string",
    "profile": {
      "bio": "string",
      "skills": ["string"],
      "experience": "string",
      "photoUrl": "string"
    }
  }
  ```

#### Update User Profile

- **Endpoint:** `PUT users/:id`
- **Description:** Update the profile information of a user.
- **Request Body:**
  ```json
  {
    "bio": "string",
    "skills": ["string"],
    "experience": "string",
    "photoUrl": "string"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Profile updated successfully",
    "profile": {
      "bio": "string",
      "skills": ["string"],
      "experience": "string",
      "photoUrl": "string"
    }
  }
  ```

### Job Postings

#### Create Job Posting

- **Endpoint:** `POST jobs`
- **Description:** Create a new job posting.
- **Request Body:**
  ```json
  {
    "title": "string",
    "description": "string",
    "requirements": "string",
    "budget": "number",
    "deadline": "date"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Job posted successfully",
    "job": {
      "id": "string",
      "title": "string",
      "description": "string",
      "requirements": "string",
      "budget": "number",
      "deadline": "date",
      "employerId": "string"
    }
  }
  ```

#### Get Job Postings

- **Endpoint:** `GET jobs`
- **Description:** Get a list of all job postings.
- **Response:**
  ```json
  {
    "jobs": [
      {
        "id": "string",
        "title": "string",
        "description": "string",
        "requirements": "string",
        "budget": "number",
        "deadline": "date",
        "employerId": "string"
      }
    ]
  }
  ```

#### Get Job Posting by ID

- **Endpoint:** `GET jobs/:id`
- **Description:** Get a job posting by its ID.
- **Response:**
  ```json
  {
    "id": "string",
    "title": "string",
    "description": "string",
    "requirements": "string",
    "budget": "number",
    "deadline": "date",
    "employerId": "string"
  }
  ```

#### Update Job Posting

- **Endpoint:** `PUT jobs/:id`
- **Description:** Update a job posting by its ID.
- **Request Body:**
  ```json
  {
    "title": "string",
    "description": "string",
    "requirements": "string",
    "budget": "number",
    "deadline": "date"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Job updated successfully",
    "job": {
      "id": "string",
      "title": "string",
      "description": "string",
      "requirements": "string",
      "budget": "number",
      "deadline": "date",
      "employerId": "string"
    }
  }
  ```

#### Delete Job Posting

- **Endpoint:** `DELETE jobs/:id`
- **Description:** Delete a job posting by its ID.
- **Response:**
  ```json
  {
    "message": "Job deleted successfully"
  }
  ```

### Proposals

#### Submit Proposal

- **Endpoint:** `POST jobs/:jobId/proposals`
- **Description:** Submit a proposal for a job posting.
- **Request Body:**
  ```json
  {
    "coverLetter": "string",
    "proposedBudget": "number",
    "estimatedTimeline": "string"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Proposal submitted successfully",
    "proposal": {
      "id": "string",
      "coverLetter": "string",
      "proposedBudget": "number",
      "estimatedTimeline": "string",
      "freelancerId": "string",
      "jobId": "string"
    }
  }
  ```

#### Get Proposals for a Job

- **Endpoint:** `GET jobs/:jobId/proposals`
- **Description:** Get all proposals for a specific job posting.
- **Response:**
  ```json
  {
    "proposals": [
      {
        "id": "string",
        "coverLetter": "string",
        "proposedBudget": "number",
        "estimatedTimeline": "string",
        "freelancerId": "string",
        "jobId": "string"
      }
    ]
  }
  ```

### Messaging

#### Send Message

- **Endpoint:** `POST messages`
- **Description:** Send a message to another user.
- **Request Body:**
  ```json
  {
    "recipientId": "string",
    "content": "string"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Message sent successfully",
    "message": {
      "id": "string",
      "senderId": "string",
      "recipientId": "string",
      "content": "string",
      "timestamp": "date"
    }
  }
  ```

#### Get Messages

- **Endpoint:** `GET messages`
- **Description:** Get all messages for the logged-in user.
- **Response:**
  ```json
  {
    "messages": [
      {
        "id": "string",
        "senderId": "string",
        "recipientId": "string",
        "content": "string",
        "timestamp": "date"
      }
    ]
  }
  ```

### Payment Processing

#### Make Payment

- **Endpoint:** `POST payments`
- **Description:** Process a payment from an employer to a freelancer.
- **Request Body:**
  ```json
  {
    "proposalId": "string",
    "amount": "number",
    "paymentMethod": "string" // e.g., "stripe", "paypal"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Payment processed successfully",
    "payment": {
      "id": "string",
      "proposalId": "string",
      "amount": "number",
      "paymentMethod": "string",
      "timestamp": "date"
    }
  }
  ```

### Reviews and Ratings

#### Submit Review

- **Endpoint:** `POST reviews`
- **Description:** Submit a review and rating for a user.
- **Request Body:**
  ```json
  {
    "userId": "string",
    "rating": "number",
    "review": "string"
  }
  ```
- **Response:**
  ```json
  {
    "message": "Review submitted successfully",
    "review": {
      "id": "string",
      "userId": "string",
      "rating": "number",
      "review": "string",
      "timestamp": "date"
    }
  }
  ```

#### Get Reviews for a User

- **Endpoint:** `GET users/:id/reviews`
- **Description:** Get all reviews for a specific user.
- **Response:**
  ```json
  {
    "reviews": [
      {
        "id": "string",
        "userId": "string",
        "rating": "number",
        "review": "string",
        "timestamp": "date"
      }
    ]
  }
  ```
