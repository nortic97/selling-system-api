# syntax=docker/dockerfile:1

# Build the application from source
FROM golang:latest AS build-stage

WORKDIR /app/models

COPY ./models ./
RUN go mod download

WORKDIR /app

COPY go.mod go.sum ./
RUN go mod download

COPY . .

RUN CGO_ENABLED=0 GOOS=linux go build -o application

# Deploy the application binary into a lean image
FROM alpine AS build-release-stage

WORKDIR /app

COPY --from=build-stage /app/docs /app/KeyService.json /app/application ./

EXPOSE 80

ENTRYPOINT ["./application"]