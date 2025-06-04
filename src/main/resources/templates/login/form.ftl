<#ftl output_format="HTML" auto_esc=true>
<!DOCTYPE html>
<#-- formLogin.ftl -->
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Login - Sistema de Ocorrências</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        :root {
            --primary-color: #4364f7;
            --secondary-color: #6fb1fc;
        }
        body {
            background: linear-gradient(135deg, var(--secondary-color), var(--primary-color));
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .auth-card {
            border-radius: 12px;
            padding: 2.5rem;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            background: white;
        }
    </style>
</head>
<body>
<div class="auth-card">
    <div class="auth-header">
        <h1 class="auth-title">Acessar Sistema</h1>
        <p class="text-muted">Informe suas credenciais para entrar</p>
    </div>

    <#-- Mensagens de erro/sucesso -->
    <#if RequestParameters.error??>
        <div class="alert alert-danger mb-4">
            Credenciais inválidas. Por favor, tente novamente.
        </div>
    </#if>

    <#if RequestParameters.logout??>
        <div class="alert alert-success mb-4">
            Você foi desconectado com sucesso.
        </div>
    </#if>

    <#if sucesso??>
        <div class="alert alert-success mb-4">${sucesso}</div>
    </#if>

    <form action="/login" method="post">
        <#if _csrf??>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </#if>

        <div class="mb-3">
            <label for="email" class="form-label">E-mail</label>
            <input type="email" class="form-control" name="username" required placeholder="seu@email.com">
        </div>

        <div class="mb-4">
            <label for="senha" class="form-label">Senha</label>
            <input type="password" class="form-control" name="password" required placeholder="Sua senha">
        </div>

        <div class="d-grid gap-2 mb-3">
            <button type="submit" class="btn btn-primary btn-lg">Entrar</button>
        </div>

        <div class="text-center">
            <p class="text-muted">Não tem uma conta? <a href="/cadastros/novo" class="text-primary">Cadastre-se</a></p>
        </div>
    </form>
</div>
</body>
</html>