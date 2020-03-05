import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-generator-survey',
  templateUrl: './generator-survey.component.html',
  styleUrls: ['./generator-survey.component.css']
})
export class GeneratorSurveyComponent implements OnInit {
  select1:number;
  select2:number;

  constructor() { }
  add: any[];



  ngOnInit() {
    this.add = [
      { brand: '1', lastYearSale: 'nombre', thisYearSale: 'privado', lastYearProfit: '', thisYearProfit: '$43,342' },
      { brand: '2', lastYearSale: '83%', thisYearSale: '96%', lastYearProfit: '$423,132', thisYearProfit: '$312,122' },
      { brand: '3', lastYearSale: '38%', thisYearSale: '5%', lastYearProfit: '$12,321', thisYearProfit: '$8,500' },
      { brand: '4', lastYearSale: '49%', thisYearSale: '22%', lastYearProfit: '$745,232', thisYearProfit: '$650,323,' },
      { brand: '5', lastYearSale: '17%', thisYearSale: '79%', lastYearProfit: '$643,242', thisYearProfit: '500,332' },
      { brand: '6', lastYearSale: '52%', thisYearSale: ' 65%', lastYearProfit: '$421,132', thisYearProfit: '$150,005' },
      { brand: '7', lastYearSale: '82%', thisYearSale: '12%', lastYearProfit: '$131,211', thisYearProfit: '$100,214' },
      { brand: '8', lastYearSale: '44%', thisYearSale: '45%', lastYearProfit: '$66,442', thisYearProfit: '$53,322' },
      { brand: '9', lastYearSale: '90%', thisYearSale: '56%', lastYearProfit: '$765,442', thisYearProfit: '$296,232' },
      { brand: '10', lastYearSale: '75%', thisYearSale: '54%', lastYearProfit: '$21,212', thisYearProfit: '$12,533' }
  ];
  }

}
