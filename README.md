# Simple hypermedia example with spring-hateoas

This example demonstrates how to use the spring-hateoas to build json in hal format.

For more information see:
- [Building a Hypermedia-Driven RESTful Web Service](https://spring.io/guides/gs/rest-hateoas/)
- [Documentacion](http://docs.spring.io/spring-hateoas/docs/current/reference/html/)


<http://localhost:8080/rest/albums/1> Accept:application/hal+json
```json
{
    "id": "1",
    "title": "Heritage",
    "artistId": "1",
    "stockLevel": 2,
    "_links": {
        "self": {
            "href": "http://localhost:8080/rest/albums/1"
        },
        "artist": {
            "href": "http://localhost:8080/rest/artist/1"
        }
    }
}
```
<http://localhost:8080/rest/albums/1?embedded=true> Accept:application/hal+json
```json
{
    "id": "1",
    "title": "Heritage",
    "artistId": "1",
    "stockLevel": 2,
    "_links": {
        "self": {
            "href": "http://localhost:8080/rest/albums/1"
        },
        "artist": {
            "href": "http://localhost:8080/rest/artist/1"
        }
    },
    "_embedded": {
        "artist": {
            "id": "1",
            "name": "Opeth",
            "_links": {
                "self": {
                    "href": "http://localhost:8080/rest/albums/1"
                }
            }
        }
    }
}
```
<http://localhost:8080/rest/albums>  Accept:application/hal+json
```json
[
    {
        "id": "3",
        "title": "Monotheist",
        "artistId": "2",
        "stockLevel": 1,
        "_links": {
            "self": {
                "href": "http://localhost:8080/rest/albums/3"
            },
            "artist": {
                "href": "http://localhost:8080/rest/artist/2"
            }
        }
    },
    {
        "id": "2",
        "title": "Deliverance",
        "artistId": "1",
        "stockLevel": 3,
        "_links": {
            "self": {
                "href": "http://localhost:8080/rest/albums/2"
            },
            "artist": {
                "href": "http://localhost:8080/rest/artist/1"
            }
        }
    }
]
```

# Running the Example

1. Open this project in Intellij.
2. Create new local tomcat Run/Debug confihuration.
3. Add new Build Artifacts in the "Before launch: Make, Build Artifacts"  and select "jersey-declarative-linking:war exploded".
5. Select available tomcat version.
6. Open deployment tab and also add "jersey-declarative-linking:war exploded".
7. Save, close and now you can run this project.
