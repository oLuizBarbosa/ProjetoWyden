<#ftl output_format="HTML" auto_esc=true>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Detalhes da Ocorrência - Sistema de Chamados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        :root { --primary-color: #4364f7; }
        body { font-family: 'Segoe UI', system-ui, sans-serif; background-color: #f8f9fa; padding-top: 20px; }
        .detail-card { max-width: 800px; margin: 0 auto; border-radius: 10px; box-shadow: 0 4px 20px rgba(0,0,0,0.1); }
    </style>
</head>
<body>

<div class="container">
    <div class="card detail-card">
        <div class="card-header bg-white">
            <h3 class="mb-0">
                <i class="bi bi-list-check"></i> Detalhes da Ocorrência
            </h3>
        </div>
        <div class="card-body">
            <div class="mb-4">
                <h4>${ocorrencia.titulo}</h4>
                <span class="badge ${(ocorrencia.status == 'ABERTO')?then('bg-warning','bg-success')}">
                    ${ocorrencia.status}
                </span>
            </div>

            <div class="mb-4">
                <h5>Descrição</h5>
                <p class="text-muted">${ocorrencia.descricao}</p>
            </div>

            <div class="row mb-4">
                <div class="col-md-6">
                    <h5>Data de Abertura</h5>
                    <p class="text-muted">
                        <i class="bi bi-calendar"></i> ${ocorrencia.dataFormatada}
                    </p>
                </div>
                <#if ocorrencia.dataFechamentoFormatada??>
                    <div class="col-md-6">
                        <h5>Data de Fechamento</h5>
                        <p class="text-muted">
                            <i class="bi bi-calendar-check"></i> ${ocorrencia.dataFechamentoFormatada}
                        </p>
                    </div>
                </#if>
            </div>

            <div class="d-flex justify-content-between">
                <a href="/ocorrencias" class="btn btn-secondary">
                    <i class="bi bi-arrow-left"></i> Voltar
                </a>
                <#if ocorrencia.status == 'ABERTO'>
                    <form action="/ocorrencias/${ocorrencia.id}/fechar" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="btn btn-success">
                            <i class="bi bi-check-circle"></i> Fechar Ocorrência
                        </button>
                    </form>
                </#if>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>