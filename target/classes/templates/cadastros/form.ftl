<#ftl output_format="HTML" auto_esc=true>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Cadastro - Sistema de Chamados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        :root {
            --primary-color: #4364f7;
        }
        body {
            font-family: 'Segoe UI', system-ui, sans-serif;
            background-color: #f8f9fa;
            padding-top: 20px;
        }
        .form-card {
            max-width: 600px;
            margin: 0 auto;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>

<#if !fieldErrors??>
    <#assign fieldErrors = {}>
</#if>

<#if erro??>
    <div class="alert alert-danger">${erro}</div>
</#if>

<div class="container">
    <div class="card form-card">
        <div class="card-header bg-white">
            <h3 class="mb-0 text-center">
                <i class="bi bi-person-plus"></i> Cadastro de Usuário
            </h3>
        </div>
        <div class="card-body">
            <form action="/cadastros" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="mb-3">
                    <label class="form-label">Nome Completo</label>
                    <input type="text" class="form-control ${(fieldErrors['nome']![])?has_content?then('is-invalid','')}"
                           name="nome" value="${cadastroDTO.nome!''}" required>
                    <#if fieldErrors?? && fieldErrors['nome']??>
                        <div class="invalid-feedback">${fieldErrors['nome']}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" class="form-control ${(fieldErrors['email']![])?has_content?then('is-invalid','')}"
                           name="email" value="${cadastroDTO.email!''}" required>
                    <#if fieldErrors?? && fieldErrors['email']??>
                        <div class="invalid-feedback">${fieldErrors['email']}</div>
                    </#if>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Senha</label>
                        <input type="password" class="form-control ${(fieldErrors['senha']??)?then('is-invalid','')}"
                               name="senha" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Confirme a Senha</label>
                        <input type="password" class="form-control ${(fieldErrors['confirmacaoSenha']??)?then('is-invalid','')}"
                               name="confirmacaoSenha" required>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label">Tipo de Usuário</label>
                    <select class="form-select ${(fieldErrors['grupo']??)?then('is-invalid','')}" name="grupo" required>
                        <option value="">Selecione...</option>
                        <#list gruposDisponiveis as role>
                            <option value="${role}" ${(cadastroDTO.grupo?? && cadastroDTO.grupo == role)?then('selected','')}>
                                ${role}
                            </option>
                        </#list>
                    </select>
                    <#if fieldErrors?? && fieldErrors['grupo']??>
                        <div class="invalid-feedback">${fieldErrors['grupo']}</div>
                    </#if>
                </div>

                <div class="d-flex justify-content-between">
                    <a href="/login" class="btn btn-secondary">
                        <i class="bi bi-arrow-left"></i> Voltar
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-check-lg"></i> Cadastrar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>