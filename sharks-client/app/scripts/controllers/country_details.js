"use strict";

angular
		.module("sharksClient")
		.controller(
				"CountryDetailsCtrl",
				[
						"$filter",
						"routingservice",
						"pageservice",
						"imagesservice",
						"countryprofiles",
						"faolex",
						"country",
						function($filter, routingservice, pageservice, imagesservice, countryprofiles, faolex,
								country) {
							var self = this;
							this.country = country;

							pageservice.setTitle(country.name);
							pageservice
									.setDescription("List of "
											+ country.name
											+ "National Plan of Action (NPOA), relevant documents and related national legislation.");

							this.groupedPoas = Stream(country.poas).sort(function(a, b) {
								if (a.poAYear === b.poAYear)
									return 0;
								if (a.poAYear > b.poAYear)
									return -1;
								return 1;
							}).groupBy(function(poa) {
								return poa.type;
							});

							this.others = Stream(country.others).sort(function(a, b) {
								if (a.year === b.year)
									return 0;
								if (a.year > b.year)
									return -1;
								return 1;
							}).toArray();

							this.dateFormat = "dd MMMM yyyy";

							this.getYearTitle = function(doc) {
								return doc.dateOfText !== null ? "Date of text: "
										+ $filter("date")(doc.dateOfText, self.dateFormat)
										: doc.dateOfOriginalText !== null ? "Date of original text: "
												+ $filter("date")(doc.dateOfOriginalText, self.dateFormat) : null;
							};

							this.getYear = function(doc) {
								return doc.dateOfText !== null ? doc.dateOfText
										: doc.dateOfOriginalText !== null ? doc.dateOfOriginalText : null;
							};

							this.docs = Stream(country.faoLexDocuments).sort(function(a, b) {
								var aYear = self.getYear(a);
								var bYear = self.getYear(b);
								if (aYear === bYear)
									return 0;
								if (aYear > bYear)
									return -1;
								return 1;
							}).toArray();

							this.flagUrl = imagesservice.countryFlagUrl(country);
							this.noFlagUrl = imagesservice.missingFlagUrl;
							this.profileUrl = countryprofiles.profileBaseUrl + country.code + "/en";
							this.faoLexUrl = faolex.baseUrl + (country.code === "EUR" ? "EC:" : "ISO:")
									+ country.code;

							this.showPoa = function(poa) {
								routingservice.toSingle("poas", poa);
							};

							this.showEntityById = function(acronym) {
								routingservice.toSingleById("entities", acronym);
							};

						} ]);
