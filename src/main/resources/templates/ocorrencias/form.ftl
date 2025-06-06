<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Nova Ocorrência</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: #f8f9fa;
            font-family: 'Segoe UI', sans-serif;
        }
        .card {
            border-radius: 1rem;
            box-shadow: 0 0.5rem 1rem rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
<div class="container py-5">
    <div class="card">
        <div class="card-header bg-white">
            <h3 class="mb-0">Nova Ocorrência</h3>
        </div>

        <div class="card-body">
            <form action="/ocorrencias" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <!-- Título -->
                <div class="mb-3">
                    <label class="form-label">Título</label>
                    <input type="text" class="form-control" name="titulo" value="${ocorrenciaDTO.titulo!''}">
                    <#if fieldErrors?? && fieldErrors['titulo']??>
                        <small class="text-danger">${fieldErrors['titulo']}</small>
                    </#if>
                </div>

                <!-- Descrição -->
                <div class="mb-3">
                    <label class="form-label">Descrição</label>
                    <textarea class="form-control" name="descricao" rows="4">${ocorrenciaDTO.descricao!''}</textarea>
                    <#if fieldErrors?? && fieldErrors['descricao']??>
                        <small class="text-danger">${fieldErrors['descricao']}</small>
                    </#if>
                </div>

                <!-- Prioridade -->
                <div class="mb-3">
                    <label class="form-label">Prioridade</label>
                    <select class="form-select" name="prioridade">
                        <#list prioridades as prioridade>
                            <option value="${prioridade}"
                                    <#if ocorrenciaDTO.prioridade?? && ocorrenciaDTO.prioridade == prioridade>
                            selected
                                    </#if>>
                                ${prioridade}
                            </option>
                        </#list>
                    </select>
                </div>

                <div class="d-flex justify-content-end">
                    <a href="/ocorrencias" class="btn btn-secondary me-2">Cancelar</a>
                    <button type="submit" class="btn btn-primary">Salvar</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>