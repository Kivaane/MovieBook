// Shared Interactivity
document.addEventListener('DOMContentLoaded', function() {
    // Add scroll effect to navbar
    const navbar = document.querySelector('.navbar');
    window.addEventListener('scroll', () => {
        if (window.scrollY > 50) {
            navbar.style.background = 'rgba(10, 14, 39, 1)';
            navbar.style.padding = '0.5rem 0';
        } else {
            navbar.style.background = 'rgba(10, 14, 39, 0.95)';
            navbar.style.padding = '1rem 0';
        }
    });

    // Auto-hide alerts
    const alerts = document.querySelectorAll('.alert');
    alerts.forEach(alert => {
        setTimeout(() => {
            alert.style.opacity = '0';
            alert.style.transition = 'opacity 0.5s ease';
            setTimeout(() => alert.remove(), 500);
        }, 5000);
    });
});
