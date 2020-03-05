import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {PersonaService} from '../services/persona.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  id: any;

  constructor(private route: Router) {

  }

  ngOnInit() {
  }



}
