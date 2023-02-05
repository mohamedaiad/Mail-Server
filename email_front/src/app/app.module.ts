import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { InboxComponent } from './inbox/inbox.component';
import { SentComponent } from './sent/sent.component';
import { TrashComponent } from './trash/trash.component';
import { DraftComponent } from './draft/draft.component';
import { ComposeComponent } from './compose/compose.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { HttpClientModule } from '@angular/common/http';
import { FoldersComponent } from './folders/folders.component';
import { ContactsComponent } from './contacts/contacts.component'
@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    SidebarComponent,
    InboxComponent,
    SentComponent,
    TrashComponent,
    DraftComponent,
    ComposeComponent,
    FoldersComponent,
    ContactsComponent,
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    NgxPaginationModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
