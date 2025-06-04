<#ftl output_format="HTML" auto_esc=true>
<!DOCTYPE html>
<#-- formCadastro.ftl -->
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Cadastro de Usuário</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilos mantidos (opcional) */
        body {
            background: linear-gradient(135deg, #6fb1fc, #4364f7);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: 'Segoe UI', sans-serif;
        }
        .card {
            border-radius: 1rem;
            padding: 2rem;
            box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.1);
            width: 100%;
            max-width: 500px;
            background: white;
        }
    </style>
</head>
<body>
<div class="card">
    <h2 class="text-center mb-4">Cadastro de Usuário</h2>

    <#-- Formulário com FreeMarker -->
    <form action="/cadastros" method="post">
        <#-- Campo Nome -->
        <div class="mb-3">
            <label class="form-label">Nome Completo</label>
            <input type="text" class="form-control" name="nome" value="${cadastroDTO.nome!''}">
            <#if fieldErrors?? && fieldErrors['nome']??>
                <small class="text-danger">${fieldErrors['nome']}</small>
            </#if>
        </div>

        <#-- Campo Email -->
        <div class="mb-3">
            <label class="form-label">E-mail</label>
            <input type="email" class="form-control" name="email" value="${cadastroDTO.email!''}">
            <#if fieldErrors?? && fieldErrors['email']??>
                <small class="text-danger">${fieldErrors['email']}</small>
            </#if>
        </div>

        <#-- Campo Senha -->
        <div class="mb-3">
            <label class="form-label">Senha</label>
            <input type="password" class="form-control" name="senha">
            <#if fieldErrors?? && fieldErrors['senha']??>
                <small class="text-danger">${fieldErrors['senha']}</small>
            </#if>
        </div>

        <#-- Campo Grupo (Select) -->
        <div class="mb-3">
            <label class="form-label">Tipo de Usuário</label>
            <select class="form-select" name="grupo">
                <option value="">Selecione...</option>
                <#list gruposDisponiveis as role>
                    <option value="${role}"
                            <#if cadastroDTO.grupo?? && cadastroDTO.grupo == role>selected</#if>>
                        ${role}
                    </option>
                </#list>
            </select>
            <#if fieldErrors?? && fieldErrors['grupo']??>
                <small class="text-danger">${fieldErrors['grupo']}</small>
            </#if>
        </div>

        <button type="submit" class="btn btn-primary w-100">Cadastrar</button>

        <div class="text-center mt-3">
            <a href="/login">Já tem conta? Faça login</a>
        </div>
    </form>
</div>
</body>
</html>