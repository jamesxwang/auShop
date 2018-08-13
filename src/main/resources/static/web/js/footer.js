app.directive("footer", function () {
   return {
       template : "<div class='container'>" +
       "  <div class='col-xs-12 col-md-8 noPadding'>" +
       "    <p class='footerLogoText'>&copy; 2018-2019 www.amazingxu.xyz</p>" +
       "  </div>" +
       "  <div class='col-xs-12 col-md-4 noPadding'>" +
       "    <div class='responsiveAlign'>" +
       "      <a href='http://www.miitbeian.gov.cn' target='_blank' class='noPadding'><p>湘ICP备18010549号-1</p></a>" +
       "    </div>" +
       "  </div>" +
       "</div>"
   };
});