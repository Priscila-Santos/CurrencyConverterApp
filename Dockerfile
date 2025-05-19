FROM openjdk:21

WORKDIR /app

# Copia os arquivos do projeto
COPY . .

# Compila o código
RUN javac -cp ".:gson-2.10.1.jar" server/CurrencyServer.java service/*.java model/*.java

# Expõe a porta
EXPOSE 8080

# Comando para rodar o servidor
CMD ["java", "-cp", ".:gson-2.10.1.jar", "server.CurrencyServer"]
