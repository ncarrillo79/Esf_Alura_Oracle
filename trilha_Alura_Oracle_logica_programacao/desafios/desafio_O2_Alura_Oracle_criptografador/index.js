
function criptografar(){
      var texto = document.getElementById('input-text').value.toLowerCase()
      // i es para que afete minúsculas e maiúsculas
      // g para toda a linha ou oração, encontra todas as coincidencias
      // m para que afete multiplas linhas , modo multilinhas

      var textEncriptado=texto.replace(/e/img,'enter')
      var textEncriptado=textEncriptado.replace(/o/img,'ober')
      var textEncriptado=textEncriptado.replace(/i/img,'imes')
      var textEncriptado=textEncriptado.replace(/a/img,'ai')
      var textEncriptado=textEncriptado.replace(/u/img,'ufat')


      document.getElementById('img').style.display='none'
      document.getElementById('text').style.display='none'
      document.getElementById('text2').innerHTML=textEncriptado
      document.getElementById('copiar').style.innerHTML="show"
      document.getElementById('copiar').style.innerHTML="inherit"
      
}


function descriptografar(){
      var texto = document.getElementById('input-text').value.toLowerCase()
      // i es para que afete minúsculas e maiúsculas
      // g para toda a linha ou oração, encontra todas as coincidencias
      // m para que afete multiplas linhas , modo multilinhas

      var textEncriptado=texto.replace(/enter/img,'e')
      var textEncriptado=textEncriptado.replace(/ober/img,'o')
      var textEncriptado=textEncriptado.replace(/imes/img,'i')
      var textEncriptado=textEncriptado.replace(/ai/img,'a')
      var textEncriptado=textEncriptado.replace(/ufat/img,'u')

      document.getElementById('img').style.display='none'
      document.getElementById('text').style.display='none'
      document.getElementById('text2').innerHTML=textEncriptado
      document.getElementById('copiar').style.display="show"
      document.getElementById('copiar').style.display="inherit"
}

function copiar() {
      var mensajem= document.getElementById('text2')
      mensajem.select()
      document.execCommand('copy')
      alert("Texto copiado!")

}



