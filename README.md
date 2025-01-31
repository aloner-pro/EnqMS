
# EnqMS

A try to make a Enquiry Management System.


## API Reference

#### Get all enquiries

```http
  GET /api/customers
```


#### Get enquiry by id

```http
  GET /api/customers/${id}
```

#### Add enquiry

```http
  POST /api/customers/${id}
```
| Type     |
|  :------- |
| `json`   |

```json
{
  "name": "Test 3",
  "products": [
    { "name": "Product A" },
    { "name": "Product B" }
  ]
}
```



## Deployment

To deploy this project run

```bash
  docker pull devsohel/enqms-app:latest
```

Exposed to port 9090

```bash
  http://localhost:9090/api/customers   
```

## Features

- Cross platform
- Working on the adding more functionality

