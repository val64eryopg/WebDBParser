document.querySelector('#collectionName')
    .addEventListener('input', e => document.querySelectorAll('[name="collectionName"]')
                .forEach(el => el.value = e.path[0].value));