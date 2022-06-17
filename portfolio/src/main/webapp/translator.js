function requestTranslate() {
    const languageCode = document.getElementById('language').value;
    const text = document.getElementById('text').value;

    const textContainer = document.getElementById('translate-result');
    textContainer.innerText = "Pending";

    const parameters = new URLSearchParams();
    parameters.append('text', text);
    parameters.append('languageCode', languageCode);

    fetch('/translated', {
        method: 'POST',
        body: parameters
    }).then(response => response.text()).then((
        translatedText
    ) => {
        textContainer.innerText = translatedText;
    });
}