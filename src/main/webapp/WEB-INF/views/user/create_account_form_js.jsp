<script type="text/javascript">
    function createAccountForm() {
        console.log('createAccountForm() CALLED!!');

        let form = document.create_account_form;

        if (form.id.value.trim() === '') {
            alert('Please enter your ID.');
            form.id.focus();
            return;
        }

        if (form.pw.value.trim() === '') {
            alert('Please enter your password.');
            form.pw.focus();
            return;
        }

        if (form.pwAgain.value.trim() === '') {
            alert('Please re-enter your password.');
            form.pwAgain.focus();
            return;
        }

        if (form.pw.value !== form.pwAgain.value) {
            alert('Passwords do not match.');
            form.pw.focus();
            return;
        }

        if (form.name.value.trim() === '') {
            alert('Please enter your name.');
            form.name.focus();
            return;
        }

        if (form.gender.value === '') {
            alert('Please select your gender.');
            form.gender.focus();
            return;
        }

        if (form.email.value.trim() === '') {
            alert('Please enter your email.');
            form.email.focus();
            return;
        }

        if (form.phone.value.trim() === '') {
            alert('Please enter your phone number.');
            form.phone.focus();
            return;
        }

        form.submit();
    }
</script>
