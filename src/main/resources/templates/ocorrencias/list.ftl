<#ftl output_format="HTML" auto_esc=true>
<!DOCTYPE html>
<html lang="pt-BR" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Minhas Ocorrências</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css">
    <style>
        :root {
            --primary-color: #4364f7;
            --sidebar-width: 280px;
        }
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f8f9fa;
        }
        .sidebar {
            width: var(--sidebar-width);
            height: 100vh;
            position: fixed;
            background: white;
            box-shadow: 2px 0 10px rgba(0,0,0,0.1);
        }
        .main-content {
            margin-left: var(--sidebar-width);
            padding: 2rem;
        }
        .ocorrencia-card {
            border-left: 4px solid var(--primary-color);
            transition: all 0.2s;
            margin-bottom: 1rem;
        }
        .ocorrencia-card:hover {
            transform: translateY(-3px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.1);
        }
        .badge-status {
            padding: 0.35rem 0.75rem;
            border-radius: 8px;
            font-weight: 600;
        }
    </style>
</head>
<body>
<!-- Sidebar -->
<div class="sidebar">
    <div class="user-profile">
        <img src="/images/profile-placeholder.png" class="profile-img" alt="Foto de perfil">
        <h5 class="mt-2" style="color: var(--primary-color);">Olá, ${usuarioLogado.nome!''}</h5>
        <span class="badge bg-light text-dark mt-1">${usuarioLogado.grupo!''}</span>
    </div>

    <nav class="nav flex-column mt-3">
        <a class="nav-link active" href="/ocorrencias">
            <i class="bi bi-house-door"></i> Dashboard
        </a>
        <!-- ... (outros itens do menu) ... -->
    </nav>
</div>

<!-- Conteúdo Principal -->
<div class="main-content">
    <div class="container-fluid">
        <div class="d-flex justify-content-between align-items-center mb-4">
            <h2 style="color: var(--primary-color);">
                <i class="bi bi-list-check"></i> Minhas Ocorrências
            </h2>
            <a href="/ocorrencias/nova" class="btn btn-primary">
                <i class="bi bi-plus-circle"></i> Nova Ocorrência
            </a>
        </div>

        <div class="card">
            <div class="card-body">
                <#if ocorrencias?? && ocorrencias?size gt 0>
                    <#list ocorrencias as ocorrencia>
                        <div class="card ocorrencia-card mb-3">
                            <div class="card-body">
                                <div class="d-flex justify-content-between align-items-start">
                                    <div>
                                        <h5 style="color: var(--primary-color);">${ocorrencia.titulo!''}</h5>
                                        <p class="text-muted mb-2">
                                            ${ocorrencia.descricao?substring(0, 100)}<#if ocorrencia.descricao?length gt 100>...</#if>
                                        </p>
                                    </div>
                                    <span class="badge-status
        <#if ocorrencia.status.name() == 'ABERTO'>bg-warning
        <#elseIf ocorrencia.status.name() == 'FECHADO'>bg-success
        <#else>bg-info</#if>">
        ${ocorrencia.status.name()}
                                    </span>
                                </div>

                                <div class="d-flex justify-content-between align-items-center mt-3">
                                    <div>
                                        <span class="badge bg-light text-dark me-2">${ocorrencia.prioridade.name()!''}</span>
                                        <small class="text-muted">
                                            Aberto em: ${ocorrencia.dataAbertura?String('dd/MM/yyyy HH:mm')}
                                        </small>
                                    </div>
                                    <a href="/ocorrencias/${ocorrencia.id}" class="btn btn-sm btn-outline-primary">
                                        <i class="bi bi-eye"></i> Detalhes
                                    </a>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <div class="text-center py-4">
                        <i class="bi bi-inbox" style="font-size: 3rem; color: #6c757d;"></i>
                        <h5 class="mt-3">Nenhuma ocorrência encontrada</h5>
                        <p class="text-muted">Crie sua primeira ocorrência clicando no botão acima</p>
                    </div>
                </#if>
            </div>
        </div>
    </div>
</div>
</body>
</html>