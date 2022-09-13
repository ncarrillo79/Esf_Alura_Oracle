export function valida(input){
      const tipoDeInput = input.dataset.tipo
  
      if(validadores[tipoDeInput]){
  
      }
  
      if(input.validity.valid){
          input.parentElement.classList.remove("input-container--invalido")
          input.parentElement.querySelector(`.input-mensagem-erro`).inner.HTML = ``
      }else{
          input.parentElement.classlist.add("input-container--invalido")
          input.parentElement.querySelector(`.input-mensagem-erro`).inner.HTML = mostraMensagemDeErro (tipoDeInput, input)
      }
  }
  
  const tiposDeErro = [
      `valueMissing`,
      `typeMismatch`,
      `patternMismatch`
  ]
  
  const mensagensDeErro = {
      nome: {
          valueMissing: `O campo Nome não pode estar vazio.`,
          patternMismatch: `O Nome deve conter somente letras e possuir até 50 caracteres.`
      },
      email: {
          valueMissing: `O campo email não pode estar vazio.`,
          typeMismatch: `O email digitado não é válido.`
      },
  
      assunto: {
          valueMissing: `O campo assunto não pode estar vazio.`
      }
  }
  
  const validadores = {
  
  }
  
  function mostraMensagemDeErro(tipoDeInput, input){
      let mensagem = ``
      tiposDeErro.forEach(erro => {
          if (input.validity[erro]) {
              mensagem = mensagensDeErro[tipoDeInput][erro]
          }
      })
  
      return mensagem 
  }
 