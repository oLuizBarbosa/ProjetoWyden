<#ftl output_format="HTML" auto_esc=true>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Ocorrências - Sistema de Chamados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        :root {
            --primary-color: #4364f7;
        }
        body {
            font-family: 'Segoe UI', system-ui, sans-serif;
            background-color: #f8f9fa;
        }
        .header-container {
            background-color: white;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            padding: 15px 0;
            margin-bottom: 30px;
        }
        .occurrence-card {
            transition: transform 0.2s;
            margin-bottom: 20px;
        }
        .occurrence-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .badge-status {
            font-size: 0.9rem;
            padding: 5px 10px;
        }
    </style>
</head>
<body>
<#if sucesso??>
    <div class="alert alert-success alert-dismissible fade show" role="alert">
        ${sucesso}
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
</#if>
<div class="header-container">
    <div class="container">
        <div class="d-flex justify-content-between align-items-center">
            <h2 class="mb-0 text-primary">
                <i class="bi bi-list-check"></i> Ocorrências
            </h2>
            <a href="/ocorrencias/nova" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Nova Ocorrência
            </a>
        </div>
    </div>
</div>

<div class="container">
    <#if ocorrencias?? && ocorrencias?size gt 0>
        <div class="row">
            <#list ocorrencias as ocorrencia>
                <div class="col-md-6">
                    <div class="card occurrence-card h-100">
                        <div class="card-body">
                            <div class="d-flex justify-content-between align-items-start">
                                <h5 class="card-title">${ocorrencia.titulo}</h5>
                                <#assign statusClass = (ocorrencia.status == 'ABERTO')?then('bg-warning', 'bg-success')>
                                <span class="badge ${statusClass} badge-status">
                                        ${ocorrencia.status}
                                    </span>
                            </div>
                            <p class="card-text text-muted">${ocorrencia.descricao?truncate(150)}</p>
                            <div class="d-flex justify-content-between align-items-center">
                                <small class="text-muted">
                                    <i class="bi bi-calendar"></i>
                                    <#if ocorrencia.dataAbertura??>
                                        ${ocorrencia.dataFormatada}
                                    <#else>
                                        Data não disponível
                                    </#if>
                                </small>
                                <a href="/ocorrencias/${ocorrencia.id}" class="btn btn-sm btn-outline-primary">
                                    <i class="bi bi-eye"></i> Detalhes
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

    <#-- Paginação -->
        <nav class="mt-4">
            <ul class="pagination justify-content-center">
                <#list 1..totalPages as p>
                    <li class="page-item ${(currentPage == p-1)?then('active','')}">
                        <a class="page-link" href="/ocorrencias?page=${p-1}&size=10">${p}</a>
                    </li>
                </#list>
            </ul>
        </nav>
    <#else>
        <div class="text-center py-5">
            <i class="bi bi-inbox" style="font-size: 3rem; color: #6c757d;"></i>
            <h4 class="mt-3 text-muted">Nenhuma ocorrência encontrada</h4>
            <a href="/ocorrencias/nova" class="btn btn-primary mt-3">
                <i class="bi bi-plus-circle"></i> Criar Primeira Ocorrência
            </a>
        </div>
    </#if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>