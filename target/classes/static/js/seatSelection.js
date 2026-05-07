let selectedSeats = [];

function toggleSeat(seatNumber) {
    const seat = document.getElementById(`seat-${seatNumber}`);
    const pricePerSeat = parseFloat(document.getElementById('moviePrice').value);
    
    if (seat.classList.contains('booked')) {
        alert('This seat is already booked!');
        return;
    }
    
    if (seat.classList.contains('selected')) {
        seat.classList.remove('selected');
        seat.classList.add('available');
        selectedSeats = selectedSeats.filter(s => s !== seatNumber);
    } else {
        seat.classList.remove('available');
        seat.classList.add('selected');
        selectedSeats.push(seatNumber);
    }
    
    updateBookingSummary(pricePerSeat);
}

function updateBookingSummary(pricePerSeat) {
    const display = document.getElementById('selectedSeatsDisplay');
    const totalPriceDisplay = document.getElementById('totalPrice');
    
    if (selectedSeats.length > 0) {
        display.innerHTML = selectedSeats.map(s => `<span class="badge badge-gold me-1">${s}</span>`).join('');
    } else {
        display.innerHTML = '<span class="text-muted">No seats selected</span>';
    }
    
    const total = selectedSeats.length * pricePerSeat;
    totalPriceDisplay.textContent = total.toFixed(2);
    
    // Update hidden inputs
    document.getElementById('selectedSeatsInput').value = selectedSeats.join('-');
    document.getElementById('totalPriceInput').value = total;
}

function proceedToPayment() {
    const hallId = document.getElementById('hallId').value;
    if (!hallId) {
        alert('Please select a hall!');
        return;
    }
    
    if (selectedSeats.length === 0) {
        alert('Please select at least one seat!');
        return;
    }
    
    document.getElementById('bookingForm').submit();
}

function initializeSeatGrid() {
    const movieId = document.getElementById('movieId').value;
    const hallId = document.getElementById('hallId').value;
    const showtime = document.getElementById('showtime').value;
    
    if (!movieId || !hallId || !showtime) return;
    
    // Fetch booked seats from API
    fetch(`/api/seats/booked?movieId=${movieId}&hallId=${hallId}&showtime=${showtime}`)
        .then(response => response.json())
        .then(bookedSeats => {
            bookedSeats.forEach(seatNum => {
                const seat = document.getElementById(`seat-${seatNum}`);
                if (seat) {
                    seat.classList.remove('available');
                    seat.classList.add('booked');
                }
            });
        })
        .catch(err => console.error('Error fetching booked seats:', err));
}

document.addEventListener('DOMContentLoaded', initializeSeatGrid);
