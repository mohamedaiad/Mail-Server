import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ComposeComponent } from './compose/compose.component';
import { ContactsComponent } from './contacts/contacts.component';
import { DraftComponent } from './draft/draft.component';
import { FoldersComponent } from './folders/folders.component';
import { InboxComponent } from './inbox/inbox.component';
import { SentComponent } from './sent/sent.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { TrashComponent } from './trash/trash.component';

const routes: Routes = [

 
  {path:'inbox',component:InboxComponent},
  {path:'sign',component:SignUpComponent},
  {path:'sent',component:SentComponent},
  {path:'trash',component:TrashComponent},
  {path:'draft',component:DraftComponent},
  {path:'compose',component:ComposeComponent},
  {path:'contacts',component:ContactsComponent},
  {path:'folder',component:FoldersComponent},
  {path:'',component:SignUpComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
