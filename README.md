# GitHub Repository Listing API

## 📌 Project Description
This API fetches a user's GitHub repositories, filters out forks, and returns their name, owner, and a list of branches with the latest commit SHA.

The project is built using **Quarkus 3** and **MicroProfile REST Client** for communication with the GitHub API.

---

## 🚀 How to Run the Project

### 1️⃣ Requirements
- Java 21+
- Maven
- GitHub account (for testing)

### 2️⃣ Clone the Repository
```sh
git clone https://github.com/yourusername/github-repo-service.git
cd github-repo-service
```

### 3️⃣ Run the Application in Development Mode

```sh
./mvnw quarkus:dev
```


The application will be available at: `http://localhost:8080`

---

## 🔥 API Endpoints

### 1️⃣ Fetch User Repositories
**GET** `/repositories/{username}`

📌 **Example Request:**
```sh
curl -X GET http://localhost:8080/repositories/octocat
```

✅ **Example Response:**
```json
[
  {
    "repositoryName": "Hello-World",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "main",
        "lastCommitSha": "6dcb09b..."
      }
    ]
  }
]
```

❌ **When User Does Not Exist (404):**
```json
{
  "status": 404,
  "message": "User not found"
}
```

---

## 🛠 Configuration
The `application.properties` file should contain:
```properties
quarkus.rest-client."org.acme.Client.GitHubClient".url=https://api.github.com
```

---

## 🧪 Testing
The project includes integration tests. Run them using:
```sh
./mvnw test
```

---

## 📜 License
This project is released under the MIT License.

---

## ✨ Author
**Wiktor Syska* – [GitHub](https://github.com/Mimirek)

