import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpinnerComponent } from './components/spinner/spinner.component';

@NgModule({
  imports: [CommonModule],
  exports: [SpinnerComponent],
  declarations: [SpinnerComponent],
  providers: []
})
export class SharedModule {}
