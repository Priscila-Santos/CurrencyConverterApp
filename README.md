# 💱 Conversor de Moedas – Full Stack (Java + React)

Um conversor de moedas com frontend moderno em **React + TypeScript + Tailwind CSS** e backend em **Java puro**, que utiliza a [ExchangeRate-API](https://www.exchangerate-api.com/) para fornecer taxas de câmbio em tempo real.

Este projeto evoluiu de uma aplicação de linha de comando para uma aplicação **full-stack** com interface web responsiva e APIs RESTful, mantendo os princípios de **código limpo e SOLID**.

---

## 🚀 Funcionalidades

### 🔙 Backend (Java)
- API RESTful para conversão de moedas e listagem de moedas disponíveis.
- Integração com a ExchangeRate-API.
- Estrutura modular com princípios **SOLID**.
- Tratamento de erros e falhas de rede com mensagens apropriadas.

### 💻 Frontend (React)
- Interface amigável e responsiva.
- Conversão em tempo real entre moedas selecionadas.
- Dropdowns dinâmicos para escolha de moedas base e alvo.
- Suporte a modo claro/escuro.
- Animações suaves com **Framer Motion**.

---

## 🛠 Tecnologias Utilizadas

### Backend:
- **Java 21+**
- **HttpClient (Java nativo)**
- **Gson** para JSON
- **ExchangeRate-API**

### Frontend:
- **React + TypeScript**
- **Tailwind CSS**
- **Framer Motion**
- **Axios**
- **Vite**

---

## 📁 Estrutura do Projeto
```
  currency-converter/
  ├── backend/
  │ ├── Main.java
  │ ├── handlers/
  │ │ └── ConversionHandler.java
  │ │ └── AvailableCurrenciesHandler.java
  │ ├── service/
  │ │ └── ExchangeRateService.java
  │ └── model/
  │ └── ExchangeRatesResponse.java
  │
  ├── frontend/
  │ ├── src/
  │ │ ├── App.tsx
  │ │ ├── main.tsx
  │ │ └── components/
  │ │ └── ThemeToggle.tsx
  │ ├── index.css
  │ └── tailwind.config.js
```

---

## ▶️ Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/seu-usuario/currency-converter.git
cd currency-converter
```

### 2. Executar o Backend (Java)
bash
Copiar
Editar
cd backend
javac Main.java
java Main
O backend estará disponível em: http://localhost:8080

### 3. Executar o Frontend (React)
bash
Copiar
Editar
cd frontend
yarn install
yarn dev
Acesse em: http://localhost:5173

⚠️ Certifique-se de que o backend está rodando antes de iniciar o frontend.

## ✅ Princípios SOLID Aplicados (Backend)

| Princípio | Implementação                                                                  |
| --------- | ------------------------------------------------------------------------------ |
| **SRP**   | Cada classe possui responsabilidade única (UI, serviço, modelo, handler).      |
| **OCP**   | Novas funcionalidades podem ser adicionadas sem alterar código existente.      |
| **LSP**   | Classes substituíveis por suas abstrações sem efeitos colaterais.              |
| **ISP**   | Interfaces e classes focadas apenas no que precisam utilizar.                  |
| **DIP**   | Módulos de alto nível dependem de abstrações, não de implementações concretas. |

## 🌐 Endpoints Disponíveis
- `GET /currencies – Lista todas as moedas disponíveis.`

- `GET /rate?base=BRL&target=USD – Retorna a taxa de câmbio entre as moedas informadas.`
