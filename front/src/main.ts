import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/app.module';

// Reemplazar la función alert() global
window.alert = (message: string, type: string = 'success') => {
  // Crear el contenedor de la notificación
  const alertContainer = document.createElement('div');
  alertContainer.classList.add('custom-alert', `alert-${type}`);
  alertContainer.textContent = message;

  // Añadir el contenedor al body
  document.body.appendChild(alertContainer);

  // Eliminar el contenedor después de 3 segundos
  setTimeout(() => {
    alertContainer.classList.add('fade-out');
    setTimeout(() => alertContainer.remove(), 500); // Eliminar después de desvanecerse
  }, 3000);
};

platformBrowserDynamic().bootstrapModule(AppModule, {
  ngZoneEventCoalescing: true,
})
  .catch(err => console.error(err));
