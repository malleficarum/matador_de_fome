$(document).ready(function () {

    // Formatando o campo de telefone
    $('#telefone').on('blur', function () {
        let phone = $(this).val();
        phone = phone.replace(/\D/g, '');
        if(phone.length === 10) {
            phone = phone.replace(/^(\d{2})(\d{4})(\d{4})/, '+55 ($1) $2-$3');
            $('#telefone-error').remove(); // Remove a mensagem de erro se estiver visível
        } else if (phone.length === 11) {
            phone = phone.replace(/^(\d{2})(\d{5})(\d{4})/, '+55 ($1) $2-$3');
            $('#telefone-error').remove(); // Remove a mensagem de erro se estiver visível
        } else {
            $(this).val(''); // Limpa o campo se o número de dígitos não for válido
            $(this).attr('placeholder', 'Informe o DDD e o número');
            $(this).addClass('invalid-phone'); // Adiciona uma classe para indicar que o telefone é inválido
            
            // Adiciona uma mensagem de erro
            $(this).after('<div id="telefone-error" class="error-message">Telefone inválido.</div>');
            
            return; // Sai da função se o número de dígitos não for válido
        }
        $(this).removeClass('invalid-phone'); // Remove a classe de telefone inválido se o número de dígitos for válido
        $('#telefone-error').remove(); // Remove a mensagem de erro se estiver visível
        $(this).val(phone);
    });
    
    
    // Buscar endereço pelo CEP
    $('#cep').on('blur', function () {
        let cep = $(this).val().replace(/\D/g, '');
        if (cep.length === 8) {
            $.getJSON(`https://viacep.com.br/ws/${cep}/json/`, function (dados) {
                if (!("erro" in dados)) {
                    $('#endereco').val(dados.logradouro);
                    $('#bairro').val(dados.bairro);
                    $('#municipio').val(dados.localidade);
                    $('#uf').val(dados.uf);
                } else {
                    $('#cep').val(''); // Limpa o campo se o CEP não for encontrado
                    $('#cep').attr('placeholder', 'CEP inválido'); // Adiciona um placeholder indicando que o CEP é inválido
                    $('#cep').addClass('invalid-cep'); // Adiciona uma classe para indicar que o CEP é inválido
                    // Adiciona uma mensagem de erro
                    $('#cep').after('<div id="cep-error" class="error-message">CEP inválido.</div>');
                }
            });
        } else {
            $('#cep').val(''); // Limpa o campo se o número de dígitos não for válido
            $('#cep').attr('placeholder', 'CEP inválido'); // Adiciona um placeholder indicando que o CEP é inválido
            $('#cep').addClass('invalid-cep'); // Adiciona uma classe para indicar que o CEP é inválido
            // Adiciona uma mensagem de erro
            $('#cep').after('<div id="cep-error" class="error-message">CEP inválido.</div>');
        }
    });
    
    // Remove a mensagem de erro ao digitar no campo de CEP novamente
    $('#cep').on('input', function () {
        $('#cep-error').remove();
        $('#cep').removeClass('invalid-cep');
    });
    
    // Validação de e-mail
    $('#email').on('blur', function () {
        let email = $(this).val();
        let regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        if (email && !regex.test(email)) {
            $('#email').val(''); // Limpa o campo se o e-mail for inválido
            $('#email').attr('placeholder', 'E-mail inválido'); // Adiciona um placeholder indicando que o e-mail é inválido
            $('#email').addClass('invalid-email'); // Adiciona uma classe para indicar que o e-mail é inválido
            // Adiciona uma mensagem de erro
            $('#email').after('<div id="email-error" class="error-message">E-mail inválido.</div>');
        }
    });
    
    // Remove a mensagem de erro ao digitar no campo de e-mail novamente
    $('#email').on('input', function () {
        $('#email-error').remove();
        $('#email').removeClass('invalid-email');
    });
});
