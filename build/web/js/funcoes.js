function limparCampos() {
    document.getElementById("txtMarca").value = '';
    document.getElementById("txtModelo").value = '';
    document.getElementById("txtMedia").value = '';
    document.getElementById("txtTempo").value = '';
    document.getElementById("txtVelocidade").value = '';
    document.getElementById("txtNome").value = '';
    document.getElementById("txtData").value = '';
}

function validarCampos() {
    var campoMarca = document.getElementById("txtMarca").value;
    var campoModelo = document.getElementById("txtModelo").value;
    var campoMedia = document.getElementById("txtMedia").value;
    var campoTempo = document.getElementById("txtTempo").value;
    var campoVelocidade = document.getElementById("txtVelocidade").value;
    var campoNome = document.getElementById("txtNome").value;
    var campoData = document.getElementById("txtData").value;

    if (campoMarca === null || campoMarca === "")
    {
        alert("Marca do Automóvel. Preenchimento obrigatório.");
        document.getElementById("txtMarca").focus();
        return false;
    }

    if (campoModelo === null || campoModelo === "")
    {
        alert("Modelo do Automóvel. Preenchimento obrigatório.");
        document.getElementById("txtModelo").focus();
        return false;
    }

    if (campoMedia === null || campoMedia === "")
    {
        alert("Média. Preenchimento obrigatório.");
        document.getElementById("txtMedia").focus();
        return false;
    }

    if (campoTempo === null || campoTempo === "")
    {
        alert("Tempo. Preenchimento obrigatório.");
        document.getElementById("txtTempo").focus();
        return false;
    }

    if (campoVelocidade === null || campoVelocidade === "")
    {
        alert("Velocidade Média. Preenchimento obrigatório.");
        document.getElementById("txtVelocidade").focus();
        return false;
    }

    if (campoNome === null || campoNome === "")
    {
        alert("Nome do Motorista. Preenchimento obrigatório.");
        document.getElementById("txtNome").focus();
        return false;
    }

    if (campoData === null || campoData === "")
    {
        alert("Data de Nascimento do Motorista. Preenchimento obrigatório.");
        document.getElementById("txtData").focus();
        return false;
    }
}

function somenteNumeros(evt, bool) {
    var codigo = (evt.which) ? evt.which : event.keyCode;
    if (bool && (document.getElementById("cboPesquisa").value === "2"))
    {
        bool = false;
    }

    if (bool)
    {
        return true;
    }

    if (!bool && (codigo === 46))
    {
        return true;
    }

    if (!bool && (codigo > 31 && (codigo < 48 || codigo > 57)))
    {
        return false;
    }

    return true;
}