const method = document.querySelector('#method');
const urlInput = document.querySelector('#url');
const headerKey = document.querySelector('#header-key');
const headerValue = document.querySelector('#header-value');
const requestBody = document.querySelector('#request-body');
const responseOutput = document.querySelector('.response-output');
const sendButton = document.querySelector('#envoyer-btn');
const addButton = document.querySelector('#add-btn');

const headers = [];

sendButton.addEventListener('click', envoyer);
addButton.addEventListener('click', addHeader);

function addHeader() {
    const key = headerKey.value.trim();
    const value = headerValue.value.trim();

    if (!key || !value) {
        responseOutput.textContent = 'Enter both a header key and a header value before adding.';
        return;
    }

    const existingHeaderIndex = headers.findIndex((header) => header.key.toLowerCase() === key.toLowerCase());

    if (existingHeaderIndex >= 0) {
        headers[existingHeaderIndex].value = value;
    } else {
        headers.push({ key, value });
    }

    headerKey.value = '';
    headerValue.value = '';
    responseOutput.textContent = formatSavedHeaders();
}

function buildHeadersObject() {
    const requestHeaders = {};

    headers.forEach((header) => {
        requestHeaders[header.key] = header.value;
    });

    const pendingKey = headerKey.value.trim();
    const pendingValue = headerValue.value.trim();

    if (pendingKey && pendingValue) {
        requestHeaders[pendingKey] = pendingValue;
    }

    return requestHeaders;
}

function formatSavedHeaders() {
    if (headers.length === 0) {
        return 'No saved headers yet.';
    }

    const lines = headers.map((header) => `${header.key}: ${header.value}`);
    return `Saved headers:\n${lines.join('\n')}`;
}

async function parseResponseBody(response) {
    const contentType = response.headers.get('content-type') || '';

    if (contentType.includes('application/json')) {
        const data = await response.json();
        return JSON.stringify(data, null, 2);
    }

    return response.text();
}

function shouldSendBody(httpMethod) {
    return !['GET', 'HEAD'].includes(httpMethod);
}

async function envoyer() {
    const selectedMethod = method.value.toUpperCase();
    const url = urlInput.value.trim();
    const body = requestBody.value.trim();
    const requestHeaders = buildHeadersObject();

    if (!selectedMethod) {
        responseOutput.textContent = 'Select an HTTP method first.';
        return;
    }

    if (!url) {
        responseOutput.textContent = 'Enter a URL before sending the request.';
        return;
    }

    const options = {
        method: selectedMethod,
        headers: requestHeaders
    };

    if (shouldSendBody(selectedMethod) && body) {
        options.body = body;
    }

    sendButton.disabled = true;
    responseOutput.textContent = 'Loading...';

    try {
        const response = await fetch(url, options);
        const responseBody = await parseResponseBody(response);
        responseOutput.textContent = responseBody || 'Empty response body.';
    } catch (error) {
        responseOutput.textContent =
            `Request failed.\n\n${error.message}\n\n` +
            'If you are calling an external API from the browser, CORS may block the request.';
    } finally {
        sendButton.disabled = false;
    }
}
