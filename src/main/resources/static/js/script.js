'use strict';

const modalLogin= document.querySelector('.modal-login') ;          // modal cely submit log
const modalRegister=document.querySelector('.modal-register');      // submit reg
const btnCloseModal = document.querySelectorAll('.close-modal');
const overlay = document.querySelector('.overlay');
const btnShowLogin =document.querySelector('.show-login');      //navbar login btn
const btnShowReg = document.querySelector('.show-register');    //navbar reg btn
const btnAddCom= document.querySelector('.addComm');        //btn na zobrazenie modal
const openComm= document.querySelector('.ratCom');          //modal form komment


const openLogin =function (){
    modalLogin.classList.remove('hidden');
    overlay.classList.remove('hidden');

}
const openRegister = function (){
    modalRegister.classList.remove('hidden');
    overlay.classList.remove('hidden');
}
const closeModal = function(){
    modalLogin.classList.add('hidden');

    modalRegister.classList.add('hidden');
    overlay.classList.add('hidden');
}

const openComme = function (){
    openComm.classList.remove('hidden');
    overlay.classList.remove('hidden');
}
//
// btnAddCom.addEventListener('click', openComme);


let badlog= $.post("/login", function (){   //async .... robim request na /login
       // alert('hahahahhahahahhaha');
        openLogin();
        // closeModal();
})

    .fail(function (){
        alert("neuspesne");
        openLogin();

    })


//
//    btnsendToDB.addEventListener('click', badlog);



btnShowLogin.addEventListener('click',openLogin);

btnShowReg.addEventListener('click', openRegister);

btnShowReg.addEventListener('click', openRegister);

btnCloseModal[0].addEventListener( 'click', closeModal);
btnCloseModal[1].addEventListener('click',closeModal);

overlay.addEventListener('click', closeModal);

document.addEventListener('keydown', function (e) {
    // console.log(e.key);

    if (e.key === 'Escape' && (!modalLogin.classList.contains('hidden') || !modalRegister.classList.contains('hidden') )) {
        closeModal();
    }
});
