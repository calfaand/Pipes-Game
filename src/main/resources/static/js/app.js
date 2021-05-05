'use strict'
const overlay = document.querySelector('.overlay');
const btnAddCom= document.querySelector('.addComm');        //btn na zobrazenie modal
const openComm= document.querySelector('.ratCom');          //modal form komment
const btnCloseModal= document.querySelector('.close-modal');


const closeModal = function(){

    openComm.classList.add('hidden');

    overlay.classList.add('hidden');
}

const openComme = function (){
    openComm.classList.remove('hidden');
    overlay.classList.remove('hidden');
}

btnAddCom.addEventListener('click', openComme);
btnCloseModal.addEventListener('click', closeModal);


overlay.addEventListener('click', closeModal);

document.addEventListener('keydown', function (e) {
    // console.log(e.key);

    if (e.key === 'Escape' && (!modalLogin.classList.contains('hidden') || !modalRegister.classList.contains('hidden') )) {
        closeModal();
    }
});
