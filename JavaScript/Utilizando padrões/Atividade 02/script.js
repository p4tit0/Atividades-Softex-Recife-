function is_valid_password(pw)
{
    if(pw == "")
        return false;
    if (pw.length < 8)
        return false;
    if (pw.length > 15)
        return false;
    if (pw.match(/[a-z]/g) && pw.match(/[A-Z]/g) && pw.match(/[0-9]/g))
        return true;
    return false;
}

function submit()
{
    if (document.getElementById("name").value === "" || document.getElementById("email").value === "" || document.getElementById("psw").value === "")
        alert("Preencha todos os campos");
    else if (!is_valid_password(document.getElementById("psw").value))
        alert("Senha inválida"); 
    else
    {

        const info = (name,gender, email, password) => `NOME: ${name}\nGÊNERO: ${gender}\nEMAIL: ${email}\nSENHA: ${password}`;
        var msg = "Cadastro efetuado com sucesso!!!\n";
        msg += "--------------------------------------\n";
        msg += info(
            document.getElementById("name").value, 
            document.querySelectorAll('input[name="gender"]:checked')[0].value, 
            document.getElementById("email").value, 
            document.getElementById("psw").value);
        alert(msg);
    }
}


