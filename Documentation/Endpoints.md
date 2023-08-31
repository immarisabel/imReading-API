# API Endpoints Documentation

VERSION: `/api/v1/`

## Books Endpoint

`.../books/`

### Create Book

**Endpoint:** `POST /books`

**Description:** Create a book object with ISBN as the ID

**Request Body**

```json
   {
  "isbn": "1234567890",
  "title": "Sample Book Title",
  "author": "Sample Author",
  "pages": 200,
  "thumbnailUrl": "thumbnail.jpg"
}
```

**Response:**

- `201 CREATED`

    - Response:

  ```json
    {
    "isbn": "1234567890",
    "title": "Sample Book Title",
    "author": "Sample Author",
    "pages": 200,
    "thumbnailUrl": "thumbnail.jpg"
    }
    ```

**Endpoint:** `GET /books`

**Description:** Get list of all books

**Response:**

- `200 OK`

  - Response:

  ```json
  [
    {
      "isbn": "1234567890",
      "title": "Sample Book Title",
      "author": "Sample Author",
      "pages": 200,
      "thumbnailUrl": "thumbnail.jpg"
    },
    {
      "isbn": "0987654321",
      "title": "Another Book Title",
      "author": "Another Author",
      "pages": 500,
      "thumbnailUrl": "thumbnail.jpg"
    }
  ]
  ```

**Endpoint:** `GET /books/{isbn}`

**Description:** Get a book based on ISBN ID

**Response:**

- `200 OK`

  - Response:

  ```json
    {
      "isbn": "1234567890",
      "title": "Sample Book Title",
      "author": "Sample Author",
      "pages": 200,
      "thumbnailUrl": "thumbnail.jpg"
    }
  ```

- `404 Resource not found`
- `601 No book found with the provided ISBN`

  - Response:

  ```json
  {
    "code": 601,
    "message": "No book found with the provided ISBN: {isbn}"
  }
  ```

**Endpoint:** `PUT /books/{isbn}`

**Description:** Update a book based on ISBN ID

**Request Body**

  ```json
  {
    "isbn": "1234567890",
    "title": "NEW Book Title",
    "author": "Sample Author",
    "pages": 200,
    "thumbnailUrl": "thumbnail.jpg"
  }
  ```

**Response:**

- `200 OK`

  - Response:

  ```json
     {
      "isbn": "1234567890",
      "title": "NEW Book Title",
      "author": "Sample Author",
      "pages": 200,
      "thumbnailUrl": "thumbnail.jpg"
    }
  ```

- `404 Resource not found`
- `601 No book found with the provided ISBN`

  - Response:

  ```json
  {
    "code": 601,
    "message": "No book found with the provided ISBN: {isbn}"
  }
  ```
  

---

## Shelves Endpoint

`.../shelves/ `
