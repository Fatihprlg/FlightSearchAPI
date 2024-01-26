Java Spring Boot API for searching and makin CRUD operations on Flights and Airports. Included basic authentication with username: user password: awesomePassword. Has a scheduled task that gets flights and update database at 12.00AM every day from a mock data. Used technologies: Java, Spring Boot, Spring Security, ModelMapper, Spring Data JPA, Awaitility, Spring Boot Test, Maven, Spring Initializr, Spring MVC, MS SQL Database, Rest Controllers, Swagger, Swagger UI.  
You can find api docs at: http://localhost:8080/v3/api-docs and swagger ui at http://localhost:8080/swagger-ui/index.html
Swagger Doc:
```
{
  "openapi": "3.0.1",
  "info": {
    "title": "OpenAPI definition",
    "version": "v0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/api/v1/flight/update_flight": {
      "post": {
        "tags": [
          "flight-controller"
        ],
        "operationId": "updateFlight",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/FlightDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FlightDto"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/flight/delete_flight/{id}": {
      "post": {
        "tags": [
          "flight-controller"
        ],
        "operationId": "deleteFlight",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/flight/add_flight": {
      "post": {
        "tags": [
          "flight-controller"
        ],
        "operationId": "addFlight",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AddFlightCommandDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FlightDto"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/airport/update_airport": {
      "post": {
        "tags": [
          "airport-controller"
        ],
        "operationId": "updateAirport",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AirportDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AirportDto"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/airport/delete_airport/{id}": {
      "post": {
        "tags": [
          "airport-controller"
        ],
        "operationId": "deleteAirport",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "string"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/airport/add_airport": {
      "post": {
        "tags": [
          "airport-controller"
        ],
        "operationId": "addAirport",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/AddAirportCommandDto"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AirportDto"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/utils/random/get_flights/{count}": {
      "get": {
        "tags": [
          "random-flights-creator"
        ],
        "operationId": "getRandomFlights",
        "parameters": [
          {
            "name": "count",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/AddFlightCommandDto"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/flight/get_flight_by_id/{id}": {
      "get": {
        "tags": [
          "flight-controller"
        ],
        "operationId": "getFlightById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/FlightDto"
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/flight/get_filtered_flights": {
      "get": {
        "tags": [
          "flight-controller"
        ],
        "operationId": "getFilteredFlights",
        "parameters": [
          {
            "name": "filter",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/FilterFlightsCommandDto"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/FlightDto"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/flight/get_all_flights": {
      "get": {
        "tags": [
          "flight-controller"
        ],
        "operationId": "getAllFlights",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/FlightDto"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/airport/get_filtered_airports": {
      "get": {
        "tags": [
          "airport-controller"
        ],
        "operationId": "getFilteredAirports",
        "parameters": [
          {
            "name": "filter",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/FilterAirportsCommandDto"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/AirportDto"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/airport/get_all_airports": {
      "get": {
        "tags": [
          "airport-controller"
        ],
        "operationId": "getAllAirports",
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "type": "array",
                  "items": {
                    "$ref": "#/components/schemas/AirportDto"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/api/v1/airport/get_airport_by_id/{id}": {
      "get": {
        "tags": [
          "airport-controller"
        ],
        "operationId": "getAirportById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/AirportDto"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "FlightDto": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "departureAirportId": {
            "type": "integer",
            "format": "int32"
          },
          "landingAirportId": {
            "type": "integer",
            "format": "int32"
          },
          "departureTime": {
            "type": "string",
            "format": "date-time"
          },
          "returnTime": {
            "type": "string",
            "format": "date-time"
          },
          "price": {
            "type": "number",
            "format": "float"
          }
        }
      },
      "AddFlightCommandDto": {
        "type": "object",
        "properties": {
          "departureAirportId": {
            "type": "integer",
            "format": "int32"
          },
          "landingAirportId": {
            "type": "integer",
            "format": "int32"
          },
          "departureTime": {
            "type": "string",
            "format": "date-time"
          },
          "returnTime": {
            "type": "string",
            "format": "date-time"
          },
          "price": {
            "type": "number",
            "format": "float"
          }
        }
      },
      "AirportDto": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "city": {
            "type": "string"
          }
        }
      },
      "AddAirportCommandDto": {
        "type": "object",
        "properties": {
          "city": {
            "type": "string"
          }
        }
      },
      "FilterFlightsCommandDto": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "departureAirportId": {
            "type": "integer",
            "format": "int32"
          },
          "landingAirportId": {
            "type": "integer",
            "format": "int32"
          },
          "departureTime": {
            "type": "string",
            "format": "date-time"
          },
          "returnTime": {
            "type": "string",
            "format": "date-time"
          },
          "price": {
            "type": "number",
            "format": "float"
          }
        }
      },
      "FilterAirportsCommandDto": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "city": {
            "type": "string"
          }
        }
      }
    }
  }
}
```
