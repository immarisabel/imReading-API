# API Endpoints Documentation

VERSION: `/api/v1/`

## Books Endpoint

`.../books/`

### Create Book

**Endpoint:** `POST /books/`

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

---

## Shelves Endpoint

`.../shelves/ `
