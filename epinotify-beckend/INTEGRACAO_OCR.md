# Integração do EpiNotify com o OCR

## Fluxo implementado

1. O frontend envia a Declaração de Óbito para `POST /api/declaracoes/upload`.
2. O backend salva o PDF e cria a declaração com status `EM_PROCESSAMENTO`.
3. Em segundo plano, o backend envia o arquivo para `POST /extrair/declaracao-obito` da API OCR.
4. O JSON completo dos nove blocos é armazenado na declaração.
5. Em caso de sucesso, o status passa para `PENDENTE`; em caso de erro, passa para `FALHA_PROCESSAMENTO`.
6. O frontend consulta `GET /api/declaracoes/{id}/ocr`, preenche os campos reconhecidos e destaca os que precisam de revisão humana.

## Ordem de inicialização

### 1. OCR — porta 8001

Na pasta `Epinotify--OCR--TCC`:

```powershell
.\.venv\Scripts\Activate.ps1
uvicorn app.main:app --reload --port 8001
```

Verificação: `http://127.0.0.1:8001/health`.

### 2. Backend — porta 8080

Na pasta interna `Epinotify--Beckend--TCC\epinotify-beckend`:

```powershell
mvn spring-boot:run
```

O PostgreSQL deve estar disponível conforme `application.properties`.

### 3. Frontend — porta 5173

Na pasta `Epinotify--Frontend--TCC`:

```powershell
npm install
npm run dev
```

Abra o endereço mostrado pelo Vite, normalmente `http://localhost:5173`.

## Contrato do processamento

O endpoint `GET /api/declaracoes/{id}/ocr` retorna:

- `declaracaoId`: identificador do registro;
- `status`: `EM_PROCESSAMENTO`, `PENDENTE` ou `FALHA_PROCESSAMENTO`;
- `processamentoConcluido`: indica se o frontend já pode consumir o resultado;
- `resultado`: JSON unificado produzido pela API OCR;
- `erro`: mensagem técnica quando o processamento falha;
- `dataProcessamento`: data e hora do término.

O tempo limite de leitura do OCR é de cinco minutos porque a extração dos nove blocos pode levar alguns minutos em CPU.

## Regra de segurança dos dados

Resultados automáticos são sugestões. Campos com baixa confiança, valor sugerido ou indicação de revisão permanecem visíveis para conferência humana antes da confirmação da declaração.
