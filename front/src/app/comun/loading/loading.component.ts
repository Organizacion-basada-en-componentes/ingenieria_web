import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { LoadingService } from '../../services/loading.service';

@Component({
  selector: 'app-loading',
  standalone: false,
  
  templateUrl: './loading.component.html',
  styleUrl: './loading.component.css'
})
export class LoadingComponent implements OnInit, OnDestroy {
  isLoading = false;
  private loadingSub!: Subscription;

  constructor(@Inject(LoadingService) private loadingService: LoadingService) {}

  ngOnInit(): void {
    this.loadingSub = this.loadingService.loadingStatus.subscribe(
      (status: boolean) => {
        this.isLoading = status;
      }
    );
  }

  ngOnDestroy(): void {
    this.loadingSub.unsubscribe();
  }
}
