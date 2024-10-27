let selectedProducts = [];
let orderCounter = 1;

function updateTotalAmount() {
  let total = 0;
  const selectedList = document.getElementById("selected-products-list");
  const productItems = selectedList.querySelectorAll(".product-item");

  selectedProducts = Array.from(productItems).map((item) => {
    return parseInt(item.id.replace("selected-", ""));
  });

  productItems.forEach((item) => {
    const priceSpan = item.querySelector(".price-badge");
    let priceText = priceSpan.textContent.replace("$", "");

    priceText = parseFloat(priceText);

    total += priceText;
  });

  const formattedTotal = total.toLocaleString("es-CO", {
    style: "currency",
    currency: "COP",
  });

  document.getElementById(
    "total-amount"
  ).textContent = `Total: ${formattedTotal}`;
}

function addProducts() {
  const checkboxes = document.querySelectorAll(".product-checkbox:checked");
  checkboxes.forEach((checkbox) => {
    const productId = checkbox.dataset.id;
    const productName = checkbox.dataset.name;
    const productPrice = checkbox.dataset.price;
    selectedProducts.push(productId);
    const productDiv = document.createElement("div");
    productDiv.className = "product-item";
    productDiv.id = `selected-${productId}`;
    productDiv.innerHTML = `
                <span>${productName}</span>
                <span class="price-badge">$${productPrice}</span>
                <button onclick="removeProductFromList(${productId})">Eliminar</button>
            `;
    document.getElementById("selected-products-list").appendChild(productDiv);
    checkbox.checked = false;
  });
  updateTotalAmount();
}

function removeProductFromList(productId) {
  // Usa filter() para eliminar el producto del array
  selectedProducts = selectedProducts.filter((id) => id !== productId);
  document.getElementById(`selected-${productId}`).remove();
  updateTotalAmount();
}

function removeProducts() {
  const selectedList = document.getElementById("selected-products-list");
  selectedProducts = []; // Asigna un array vacío para vaciar el array
  selectedList.innerHTML = "";
  updateTotalAmount();
}

function generateOrder() {
  if (selectedProducts.length === 0) {
    alert("Por favor seleccione al menos un producto");
    return;
  }
  const productIds = selectedProducts;

  fetch("/orders", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(productIds),
  })
    .then((response) => response.json())
    .then((orders) => {
      const tableBody = document.getElementById("orders-table-body");
      let total = 0;

      orders.forEach((order, index) => {
        const row = document.createElement("tr");
        total += order.product.price;

        row.innerHTML = `
                      <td>${orderCounter}</td>
                      <td>${order.product.name}</td>
                      <td>$${order.product.price.toFixed(2)}</td>
                      <td>${
                        index === orders.length - 1
                          ? "$" + total.toFixed(2)
                          : ""
                      }</td>
                    `;
        tableBody.appendChild(row);
      });
      orderCounter++;
      removeProducts();
    })
    .catch((error) => {
      console.error("Error:", error);
      alert("Error al generar el pedido");
    });
}
