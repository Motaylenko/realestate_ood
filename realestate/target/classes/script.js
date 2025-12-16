const API_URL = 'http://localhost:8080';
const messageDiv = document.getElementById('message');
const tableBody = document.getElementById('tableBody');

// Завантажити дані при старті
loadPaychecks();

function showMessage(text, type) {
    messageDiv.textContent = text;
    messageDiv.className = `message ${type} show`;
    setTimeout(() => {
        messageDiv.className = 'message';
    }, 3000);
}

async function loadPaychecks() {
    try {
        const response = await fetch(`${API_URL}/api/paychecks`, {
            method: 'GET',
            mode: 'cors'
        });

        if (response.ok) {
            const paychecks = await response.json();
            updateTable(paychecks);
        } else {
            const errorText = await response.text();
            showMessage('Помилка завантаження даних: ' + errorText, 'error');
            tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Помилка: ' + errorText + '</td></tr>';
        }
    } catch (error) {
        showMessage('Помилка з\'єднання з сервером', 'error');
        tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Помилка з\'єднання з сервером: ' + error.message + '</td></tr>';
    }
}

function updateTable(paychecks) {
    if (paychecks.length === 0) {
        tableBody.innerHTML = '<tr><td colspan="3" class="empty-state">Немає даних</td></tr>';
        return;
    }

    tableBody.innerHTML = paychecks.map((item, index) => `
        <tr>
            <td>${index + 1}</td>
            <td>${item.amount.toFixed(2)} грн</td>
            <td>${item.payDate}</td>
        </tr>
    `).join('');
}
