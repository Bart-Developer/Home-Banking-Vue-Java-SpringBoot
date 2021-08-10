<template lang="pug">

div.classWrap.animate__animated.animate__fadeIn
    
    .modal-loans.h-50
        .form
            form          
            span Type Loan
            select.text-white(v-model="loanSelected")
                option.bg-secondary(value='' disabled) Loan Type
                option.bg-secondary(v-for="x in loans" :key="x.id" :value="x.payments") {{x.name}}
           
            span Payments
            select.text-white(v-model='paySelected')
                option.bg-secondary(value='' disabled) Payments
                option.bg-secondary(v-for="x in loanSelected" :key="x.id") {{x}}
            
            span To Account
            select.text-white(v-model='account')
                option.bg-secondary(value='' disabled) Account
                option.bg-secondary(v-for="x in client.accounts" :key="x.id") {{x.number}}

            label(for='num' required) Amount
            input.num(type='text' maxlength="7" v-model="amount" @click="commaNums()" )
    
            hide-at(breakpoint="medium")
            .close(@click="close()")
            
            button(type='button' @click="requestLoan()") Apply for
        .invite
            h3.mb-2.text-decoration-underline information
            h5.mb-3 Loans have a maximum amount according to their type:
            h5.mb-1 Mortgage Max Amount: $ 500.000
            h5.mb-1 Personal Max Amount: $ 100.000
            h5.mb-5 Motor-Vehicle Max Amount: $ 300.000 
            h3.text-decoration-underline.mb-2 Calculator
            h4.text-decoration-underline.fw-bold.mb-4 Your selection 
            h5 This type:             Personal
            h5 This amount:       $ {{amount.replace(/\,/g, '')}} + IVA (20%)
            h5.mb-4 This way to pay:   {{paySelected}} installments
            h5 Then you would have to pay:
            h5(v-if="amount != '$00.00' && amount!=''")  -- {{paySelected}} installments of $ {{(amount / paySelected).toFixed(2)}} --
             
            .nope(@click="close()") No thanks, I dont like the idea
            .close(@click="close()")

</template>

<script>
 
export default {
    data(){
      return{
        loanSelected:'',
        paySelected:0,
        amount:'$00.00',
        account:'',
        loans:[],
        client:[],
        paintCal:[],
    }},
    created(){

        this.client = this.$store.state.data;

        axios.get('/api/loans')
        .then(({data}) => {this.loans = data})
    },
    methods:{
        close(){
          setTimeout(() => this.$emit("setView", "loans"), 400)
        },
        requestLoan(){

            let amount = this.amount.replace(/\,/g, '') // le quito las comas al amount ingresado
            let [located] = this.loans.filter(x => x.payments.length == this.loanSelected.length)
            
            if(located==null || this.paySelected == '' || this.account == ''){
              return alert('Please complete all the information in the form')
            }

            if (isNaN(amount) || amount < 5000 || amount > located.maxamount) {
                this.amount= 'incorrect value';
                $('input[class=num]').addClass('bg-danger' + ' animate__animated animate__flash');
                return ''          
            }else {
                return axios.post("/api/clients/current/loan",{id:located.id,name:located.name, amount:amount,payment:this.paySelected,accountSet:this.account})
                .then(()=> {this.$emit("setView", "loans")})
                .catch(({response})=> swal({
                title: response.data,
                icon: "warning",
                buttons: true,
                }))      
                }
            
            alert('Ups Some errors unexpected happens')
        },    
        commaNums(){ 
            $("input").removeClass('bg-danger' + ' animate__animated animate__flash');
            $('input[class=num]').val('');
            function updateTextView(_obj){
                var num = getNumber(_obj.val());
                if(num==0){
                _obj.val('');
                }else{
                _obj.val(num.toLocaleString('en-GB', {maximumFractionDigits: 1}));
                }
               return _obj.val();
            }
            function getNumber(_str){
                var arr = _str.split('');
                var out = new Array();
                for(var cnt=0;cnt<arr.length;cnt++){
                if(isNaN(arr[cnt])==false){
                out.push(arr[cnt]);
                }
                }
                return Number(out.join(''));
            }
            $(document).ready(function(){
            $('input[class=num]').on('keyup',function(){
            updateTextView($(this)); 
             
            });
            });
            
         
        },
        },
    mounted(){
      
    // $('.modal-loans').show()

    $(".close, .nope").on('click', function () {
    $('.modal-loans').addClass('hidden');
    $('.open').addClass('active');
    })

    // $(".open").on('click', function () {
    // $(this).removeClass('active');
    // $('.modal-loans').removeClass('hidden');
    // })

    }
    }
</script>


<style lang="scss" scoped>

@import url('https://fonts.googleapis.com/css2?family=Roboto:ital,wght@1,300&display=swap');

*, :before,:after {
box-sizing: border-box;
transition: .4s;

}

@media only screen and (max-width: 550px) {
    .invite{
      display: none;
    }
    .form{
    width: 100%!important;
    }
    .modal-loans{
      width: 95vw!important;
      height: 80vh!important;
    }
  
  }

@media only screen and (max-width: 1000px) {
    .modal-loans{
      width: 95vw!important;
      height: 80vh!important;
      left:0!important
    } 
  }

.classWrap {
  width: 100vw;
  background: rgba(#474747, .8);
  height: 100vh;
  overflow: hidden;
  color: #FFF;
  font-family: Roboto;
  transition: 0s!important;
}

.modal-loans {
  min-height: 70%;
  width: 50vw;
  margin: auto;
  position: absolute;
  top: 0; bottom: 0;
  left: 20%; right: 0;
  transition: .4s, box-shadow .3s .4s;
  &.hidden {
    box-shadow: none;
    transition: .4s, box-shadow 0s;
    opacity: 0;
    visibility: hidden;
    .form {
      top: 100%;
    }
    .invite {
      top: -100%;
      .close {
        height: 0;
        width: 0;
        top: 0;
        right: 0;
      }
    }
  }
}

.form, .invite {
  background: rgba(#474747, .8);
  width: 50%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  padding: 35px 35px 35px 35px;
  margin-top: 11vh;
}

input {
  background: rgba(#FFF, .15);
  width: 100%;
  padding: 8px;
  margin: 15px 0;
  border: none;
  border-radius: 3px;
  outline: none;
  color: #FFF;
  font-size: 20px;
  &:-webkit-autofill {
    box-shadow: 0 0 0px 500px lighten(#474747, 20%) inset;
    -webkit-box-shadow: 0 0 0px 500px lighten(#474747, 20%) inset;
    -webkit-text-fill-color: #FFF;
  }
}

select{
  background: rgba(#FFF, .15);
  width: 100%;
  padding: 8px;
  margin: 15px 0;
  border: none;
  border-radius: 3px;
  outline: none;
  color: #FFF;
  font-size: 20px;
  &:-webkit-autofill {
    box-shadow: 0 0 0px 500px lighten(#474747, 20%) inset;
    -webkit-box-shadow: 0 0 0px 500px lighten(#474747, 20%) inset;
    -webkit-text-fill-color: #FFF;
  } 
}

option *{
  background: rgba(#FFF, .15);
  color: #000;
}

label {
  font: 500 17px Roboto;
  color:rgb(246, 250, 245);
  text-shadow: 1px #c5c5c5;  
}

span{
  font: 500 17px Roboto;
  color:rgb(246, 250, 245);
  text-shadow: 1px #c5c5c5;
}

button {
  background: linear-gradient( -45deg, #0a504a, #30c569) !important;
  padding: 10px 20px;
  border: none;
  border-radius: 21px;
  outline: none;
  overflow: hidden;
  position: absolute;
  bottom: 30px;
  left: 50%;
  color: #FFF;
  font-size: 18px;
  cursor: pointer;
  transform: translateX(-50%);
   &:hover:before {
    left: 110%;
    transition: .3s;
  }
  &:before {
    content: '';
    background: rgba(#FFF, .3);
    height: 100%;
    width: 65px;
    position: absolute;
    top: 0;
    left: -100%;
    transform: skew(-45deg);
    transition: 0s;
  }
}

.invite {
  background: linear-gradient( -45deg, #0a504a, #30c569);
  left: 50%;
}

h3 {
  font: 30px Roboto;
  text-transform: uppercase;
}

.nope {
  color: white;
  background: #474747;
  padding: 10px 15px;
  border-radius: 3px;
  box-shadow: 0 2px 5px rgba(#000, .25);
  position: absolute;
  bottom: 30px;
  left: 50%;
  font-size: 12px;
  white-space: nowrap;
  cursor: pointer;
  transform: translateX(-50%);
   &:hover {
    box-shadow: 0 5px 10px rgba(#000, .25);
    transform: translateX(-50%);
  }
}

.close {
  background: #474747;
  height: 30px;
  width: 30px;
  border: 3px solid #FFF;
  border-radius: 50%;
  position: absolute;
  top: 0;
  right: 0;
  cursor: pointer;
  transition: .4s .3s;
   &:before, &:after {
    content: '';
    background: #FFF;
    height: 80%;
    width: 3px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%) rotate(-45deg);
  }
  &:after {
   transform: translate(-50%, -50%) rotate(45deg); 
  } 
}

.open {
  background: linear-gradient( -45deg, #f857a6 , #ff5858);
  height: 45px;
  width: 150px;
  padding: 10px 20px;
  margin: auto;
  position: absolute;
  top: 0; bottom: 0;
  left: 0; right: 0;
  font-size: 20px;
  text-align: center;
  white-space: nowrap;
  cursor: pointer;
  opacity: 0;
  visibility: hidden;
   &.active {
    opacity: 1;
    visibility: visible;
  }
  &:hover {
    box-shadow: 0 5px 15px rgba(#000, .3);
  } 
}
</style>