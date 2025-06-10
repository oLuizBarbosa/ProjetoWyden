<#ftl output_format="HTML" auto_esc=true>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Nova Ocorrência - Sistema de Chamados</title>
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
            max-width: 800px;
            margin: 0 auto;
            border-radius: 10px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
        }
        .required-field::after {
            content: " *";
            color: red;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="card form-card">
        <div class="card-header bg-white">
            <h3 class="mb-0 text-center">
                <i class="bi bi-plus-circle"></i> Nova Ocorrência
            </h3>
        </div>
        <div class="card-body">
            <form action="/ocorrencias" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="mb-3">
                    <label class="form-label required-field">Título</label>
                    <input type="text" class="form-control ${(fieldErrors['titulo']??)?then('is-invalid','')}"
                           name="titulo" value="${ocorrenciaDTO.titulo!''}" required>
                    <#if fieldErrors?? && fieldErrors['titulo']??>
                        <div class="invalid-feedback">${fieldErrors['titulo']}</div>
                    </#if>
                </div>

                <div class="mb-3">
                    <label class="form-label required-field">Descrição</label>
                    <textarea class="form-control ${(fieldErrors['descricao']??)?then('is-invalid','')}"
                              name="descricao" rows="5" required>${ocorrenciaDTO.descricao!''}</textarea>
                    <#if fieldErrors?? && fieldErrors['descricao']??>
                        <div class="invalid-feedback">${fieldErrors['descricao']}</div>
                    </#if>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label class="form-label required-field">Prioridade</label>
                        <select class="form-select ${(fieldErrors['prioridade']??)?then('is-invalid','')}"
                                name="prioridade" required>
                            <option value="">Selecione...</option>
                            <#list prioridades as prioridade>
                                <option value="${prioridade}"
                                        ${(ocorrenciaDTO.prioridade?? && ocorrenciaDTO.prioridade == prioridade)?then('selected','')}>
                                    ${prioridade}
                                </option>
                            </#list>
                        </select>
                        <#if fieldErrors?? && fieldErrors['prioridade']??>
                            <div class="invalid-feedback">${fieldErrors['prioridade']}</div>
                        </#if>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label class="form-label">Status</label>
                        <input type="text" class="form-control"
                               value="${ocorrenciaDTO.status!'ABERTO'}" readonly>
                        <input type="hidden" name="status" value="${ocorrenciaDTO.status!'ABERTO'}">
                    </div>
                </div>

                <div class="d-flex justify-content-between mt-4">
                    <a href="/ocorrencias" class="btn btn-secondary">
                        <i class="bi bi-arrow-left"></i> Cancelar
                    </a>
                    <button type="submit" class="btn btn-primary">
                        <i class="bi bi-save"></i> Salvar Ocorrência
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>