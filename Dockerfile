FROM openjdk:17-jdk-slim
# Đặt working directory
WORKDIR /app
# Copy file jar từ target vào container
COPY target/notes-0.0.1-SNAPSHOT.jar app.jar
# Chạy ứng dụng
ENTRYPOINT ["java","-jar","app.jar"]

